package com.cczu.model.lydw.listener;

import com.cczu.model.lydw.entity.LYDW_BJJL;
import com.cczu.model.lydw.entity.Pub_FileTime;
import com.cczu.model.lydw.entity.Pub_FileTimeHis;
import com.cczu.model.lydw.service.LYDW_RydwService;
import com.cczu.model.lydw.service.LYDW_BjjlService;
import com.cczu.websocket.LYDW_WebSocket;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MsgThread implements Runnable {
    private final Logger LOGGER = Logger.getLogger(MsgThread.class);
    private ConcurrentLinkedQueue<Pub_FileTime> rcvMsgQu = MsgServer.getInstance().getRcvMsgQu();
    public static final String FLOOR_FIRST = ConfigProperties.getString("floor_first", "");
    public static final String FLOOR_SECOND = ConfigProperties.getString("floor_second", "");
    public static final String FLOOR_THIRD = ConfigProperties.getString("floor_third", "");
    public static final String FLOOR_FOUR = ConfigProperties.getString("floor_four", "");
    public static final String FLOOR_FIVE = ConfigProperties.getString("floor_five", "");
    private LYDW_RydwService lydwRydwService = (LYDW_RydwService) ContextLoader.getCurrentWebApplicationContext().getBean("LYDW_RydwService");
    private LYDW_BjjlService lydwBjjlService = (LYDW_BjjlService) ContextLoader.getCurrentWebApplicationContext().getBean("LYDW_BjjlService");

    @Override
    public void run() {
        while (true) {
            if (rcvMsgQu.isEmpty()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOGGER.error("MsgThread线程睡眠异常：" + e.getMessage());
                }
            } else {
                try {
                    Pub_FileTime rcvMsg = (Pub_FileTime) rcvMsgQu.poll();
                    if (null == rcvMsg) {
                        continue;
                    }
                    String file = rcvMsg.getTagId();
                    Pub_FileTime pubFileTime = new Pub_FileTime();
                    Pub_FileTimeHis pubFileTimeHis = new Pub_FileTimeHis();
                    List<LYDW_BJJL> bjjls;
                    if(lydwRydwService.findPubFileroomTime(file)){
                        pubFileTime = lydwRydwService.getPubFileroomTime(file);
                        if((pubFileTime.getOnlineStatus() == rcvMsg.getOnlineStatus()) && pubFileTime.getOnlineStatus() == 2){
                            continue;
                        }
                        if(rcvMsg.getOnlineStatus() == -2){
                            rcvMsg.setOnlineStatus(pubFileTime.getOnlineStatus());
                        }
                        rcvMsg.setId(pubFileTime.getId());
                        switch (rcvMsg.getOnlineStatus()){
                            case 1:

                                break;
                            case 2:
                                rcvMsg.setX(pubFileTime.getX());
                                rcvMsg.setY(pubFileTime.getY());
                                rcvMsg.setZ(pubFileTime.getZ());
                                break;
                        }
                        if(rcvMsg.getIsAlarm() == 1){
                            rcvMsg.setX(pubFileTime.getX());
                            rcvMsg.setY(pubFileTime.getY());
                            rcvMsg.setZ(pubFileTime.getZ());
                            rcvMsg.setOnlineStatus(pubFileTime.getOnlineStatus());
                            LYDW_BJJL bjjl = new LYDW_BJJL();
                            bjjl.setStatus(0);
                            bjjl.setTime(rcvMsg.getTime());
                            bjjl.setMsg(lydwRydwService.getYgName(rcvMsg.getTagId()) + "主动报警！");
                            LYDW_WebSocket.sendMessage("BJ:{\"msg\":\"" + lydwRydwService.getYgName(rcvMsg.getTagId()) + "主动报警！\"}" );
                            bjjl.setType("人员报警");
                            bjjl.setX(rcvMsg.getX());
                            bjjl.setY(rcvMsg.getY());
                            bjjl.setZ(rcvMsg.getZ());
                            lydwBjjlService.addBjjl(bjjl);
                        }
                    }
                    bjjls = lydwRydwService.alarmInfo(rcvMsg);
                    if(!bjjls.isEmpty()){
                        rcvMsg.setIsAlarm(1);
                        Iterator<LYDW_BJJL> it = bjjls.iterator();
                        while(it.hasNext()){
                            LYDW_BJJL bjjl = it.next();
                            if(lydwBjjlService.findOne(bjjl.getXbid(), (int)bjjl.getWlid())){
                                continue;
                            }
                            LYDW_WebSocket.sendMessage("BJ:{\"msg\":\"" + bjjl.getMsg() + "\"}");
                            lydwBjjlService.addBjjl(bjjl);
                        }
                    }
                    lydwRydwService.addInfo(rcvMsg);
                    pubFileTimeHis.setTagId(rcvMsg.getTagId());
                    pubFileTimeHis.setX(rcvMsg.getX());
                    pubFileTimeHis.setY(rcvMsg.getY());
                    pubFileTimeHis.setZ(rcvMsg.getZ());
                    pubFileTimeHis.setTime(rcvMsg.getTime());
                    pubFileTimeHis.setIsAlarm(rcvMsg.getIsAlarm());
                    lydwRydwService.addHisInfo(pubFileTimeHis);
                    LYDW_WebSocket.sendMessage("UD:" + lydwRydwService.getByTagId(rcvMsg.getTagId()));
                } catch (Exception e) {
                    LOGGER.info(String.format("MsgProcessor异常：%s，全部异常信息：%s", e.getMessage(), e));
                    LOGGER.info("MsgProcessor异常：" + e.getMessage());
                }
            }
        }


    }
}
