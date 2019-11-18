package com.cczu.model.lydw.listener;

import com.cczu.model.lydw.entity.BluetoothLocation;
import com.cczu.model.lydw.entity.Pub_FileTime;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MsgServer implements ServletContextListener {
    private final Logger LOGGER = LoggerFactory.getLogger(MsgServer.class);
    //单例
    private static class SingletonHolder {
        private static final MsgServer msgServer = new MsgServer();
    }

    public static MsgServer getInstance() {
        return SingletonHolder.msgServer;
    }

    private static int RETRY = 4;
    private boolean isBind = false;
    private boolean isOpen = false;
    public static final int PORT = ConfigProperties.getInt("MsgSvr_port", 8860);
    public static Date startTime = new Date();
    public NioDatagramAcceptor dataAccepter = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        while (!this.isBind && RETRY > 0) {
            try {
                this.isBind = MsgServer.getInstance().start();
            } catch (Exception e) {
                isOpen = false;
                LOGGER.error("MsgServer启动失败:" + e);
                RETRY--;
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException ex) {
                    this.LOGGER.trace(ex.getMessage());
                }
            }
        }

    }

    public static MsgHandler msgHandler = MsgHandler.getInstance();

    //创建接收Msg队列,队列中的Msg在独立的MsgProcessor线程中处理
    private ConcurrentLinkedQueue<Pub_FileTime> rcvMsgQu = new ConcurrentLinkedQueue<Pub_FileTime>();
    private int rxMsgProcThreadCount = ConfigProperties.getInt("RxMsgProcThreadCount", 1);

    public ConcurrentLinkedQueue<Pub_FileTime> getRcvMsgQu() {
        return rcvMsgQu;
    }

    private int threadCount = rxMsgProcThreadCount;
    private ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    public static LinkedList<Pub_FileTime> newestMsgList = new LinkedList<Pub_FileTime>();
    public boolean start() throws IOException {
        //clearSessionState();
        LOGGER.info("MsgServer监听服务端开始启动……");
        dataAccepter = new NioDatagramAcceptor();
        LoggingFilter log = new LoggingFilter();
        log.setMessageReceivedLogLevel(LogLevel.INFO);
        dataAccepter.getFilterChain().addLast("logger", log);
        dataAccepter.getFilterChain()
                .addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));


        //传入编码、解码方式
        dataAccepter.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MsgCodecFactory()));
        dataAccepter.getSessionConfig().setReadBufferSize(1024);
        dataAccepter.setHandler(msgHandler);
        //启动独立的RcvMsgQu处理线程
        for (int i = 0; i < rxMsgProcThreadCount; i++) {
            executorService.execute(new MsgThread());
        }
        // ** UDP通信配置
        DatagramSessionConfig dcfg = dataAccepter.getSessionConfig();
        dcfg.setReuseAddress(true);
        // 设置输入缓冲区的大小，压力测试表明：调整到2048后性能反而降低
        dcfg.setReceiveBufferSize(1024);
        // 设置输出缓冲区的大小，压力测试表明：调整到2048后性能反而降低
        dcfg.setSendBufferSize(1024);
        dataAccepter.bind(new InetSocketAddress(PORT));
        LOGGER.info("MsgServer启动成功!端口号:" + PORT);
        isOpen = true;

        return isOpen;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        LOGGER.info("Connection Service Destroyed...");
    }

    public IoSession getSession(long sid) {
        return dataAccepter.getManagedSessions().get(sid);
    }

    public IoAcceptor getIoAcceptor() {
        return dataAccepter;
    }

    public static void saveNewestMsg(Pub_FileTime rcvOtaMsg) {
        LinkedList<Pub_FileTime> list = MsgServer.newestMsgList;
        list.add(rcvOtaMsg);
        if (list.size() > 10) {
            list.remove(0);
        }
    }
}
