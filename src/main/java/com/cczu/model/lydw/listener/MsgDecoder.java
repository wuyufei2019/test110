package com.cczu.model.lydw.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cczu.model.lydw.entity.Pub_FileTime;
import com.cczu.model.lydw.service.LYDW_RydwService;
import com.cczu.model.lydw.service.Pub_FileService;
import com.cczu.util.common.PositonConvert;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.springframework.web.context.ContextLoader;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 解码器
 */
public class MsgDecoder extends CumulativeProtocolDecoder {

    private Pub_FileService pubFileService = (Pub_FileService) ContextLoader.getCurrentWebApplicationContext().getBean("Pub_FileService");

    private Logger LOGGER = Logger.getLogger(MsgDecoder.class);
    public static final CharsetDecoder decoder = (Charset.forName("UTF-8")).newDecoder();


    public MsgDecoder(Charset charset) {
        charset.newDecoder();
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        Pub_FileTime pubFileTime = new Pub_FileTime();
        String orgin = in.getString(decoder);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Aisa/Shanghai"));
        String jsonStr = orgin.substring(orgin.indexOf("{") , orgin.lastIndexOf(","));
        String timeStr = orgin.substring(orgin.indexOf("{") - 17, orgin.indexOf("{") - 1);
        long timeStamp = Long.valueOf(timeStr, 16);
        Date datetime = new Timestamp(timeStamp);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String type = jsonObject.getString("method");
        JSONObject params = jsonObject.getJSONObject("params");
        switch (type) {
            case "Location":
                System.out.println("定位数据");
                pubFileTime.setTagId(params.getString("userId"));
                pubFileTime.setX(params.getFloat("x"));
                if(params.getFloat("floor") > 3){
                    pubFileTime.setY(0);
                }else{
                    pubFileTime.setY(params.getFloat("floor") - 1);
                }
                pubFileTime.setZ(params.getFloat("y"));
                pubFileTime.setTime(datetime);
                switch (params.getInteger("direction")){
                    case 1:
                        pubFileTime.setOnlineStatus(1);
                        break;
                    case 5:
                        pubFileTime.setOnlineStatus(0);
                        break;
                    default:
                        pubFileTime.setOnlineStatus(-1);
                }
                MsgServer.getInstance().getRcvMsgQu().add(PositonConvert.convert(pubFileTime));
                break;
            case "Ping":
                System.out.println("Ping数据");
                if(params.getString("deviceType").equals("Tag")){
                    pubFileTime.setTagId(pubFileService.findByTagId(params.getString("uid")));
                    pubFileTime.setTime(datetime);
                    if(params.getBoolean("silent")){
                        pubFileTime.setOnlineStatus(2);
                        MsgServer.getInstance().getRcvMsgQu().add(pubFileTime);
                    }
                }
                break;
            case "PressKey":
                System.out.println("用户报警数据");
                if(params.getString("deviceType").equals("Tag")){
                    pubFileTime.setTagId(pubFileService.findByTagId(params.getString("uid")));
                    pubFileTime.setTime(datetime);
                    pubFileTime.setIsAlarm(1);
                    MsgServer.getInstance().getRcvMsgQu().add(pubFileTime);
                }
            default:
                System.out.println("数据异常！");
        }
        in.reset();
        return true;
    }
}