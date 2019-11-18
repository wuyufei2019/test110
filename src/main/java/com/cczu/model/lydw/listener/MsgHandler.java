package com.cczu.model.lydw.listener;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class MsgHandler extends IoHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MsgHandler.class);
    public static final CharsetDecoder decoder = (Charset.forName("UTF-8")).newDecoder();

    private static class SingletonHolder {
        private static final MsgHandler evBoxServerHandler = new MsgHandler();
    }

    public static MsgHandler getInstance() {
        return SingletonHolder.evBoxServerHandler;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable e){
        LOGGER.info(String.format("通讯异常,原因为:%s,全部的异常信息如下:%s", e.getMessage(), e));
    }

    @Override
    public void messageReceived(IoSession session, Object msg)throws Exception {
        super.messageReceived(session,msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("server session created");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus idle) throws Exception {
        Long sessionId = session.getId();
        if (null != sessionId){
            LOGGER.info(String.format("终端空闲超时即将断开,sessionId:%s",sessionId));
        } else {
            LOGGER.info("关闭无效的的Session,sessionId : " + session.getId());
        }
        sessionClosed(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        session.close(true);
    }
}
