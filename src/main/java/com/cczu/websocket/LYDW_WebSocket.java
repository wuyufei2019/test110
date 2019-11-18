package com.cczu.websocket;

import com.cczu.model.lydw.service.LYDW_RydwService;
import com.cczu.sys.system.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/lydwWebsocket", configurator = HttpSessionConfigurator.class)
@Component
public class LYDW_WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<LYDW_WebSocket> webSocketSet = new CopyOnWriteArraySet<LYDW_WebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        User user = (User)httpSession.getAttribute("user");
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();
        if ("1".equals(user.getUsertype())) {//企业用户获取蓝牙位置信息
            prepareData(user.getId2());
        }
        System.out.println("webSocket有新连接加入！");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }



    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        webSocketSet.remove(this);
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public static void sendMessage(String message) throws IOException {
        for (LYDW_WebSocket item : webSocketSet) {
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        LYDW_WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        LYDW_WebSocket.onlineCount--;
    }

    /**
     * 获取所有的位置信息
     * @param qyid
     */
    private void prepareData(Long qyid) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("minute", "5");
            map.put("qyid", qyid);
            String jsonStr = ((LYDW_RydwService) ContextLoader.getCurrentWebApplicationContext().getBean(
                    "LYDW_RydwService")).getAllPubFileTime();
            sendMessage("DW:"+jsonStr);
            String numStr = ((LYDW_RydwService) ContextLoader.getCurrentWebApplicationContext().getBean(
                    "LYDW_RydwService")).totalOnlinePoeple(map);
            sendMessage("TJ:"+numStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
