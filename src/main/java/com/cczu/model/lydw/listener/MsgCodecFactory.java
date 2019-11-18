package com.cczu.model.lydw.listener;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

public class MsgCodecFactory implements ProtocolCodecFactory {
    private final MsgEncoder encoder;
    private final MsgDecoder decoder;

    public MsgCodecFactory() {
        this.encoder = new MsgEncoder(Charset.forName("utf-8"));
        this.decoder = new MsgDecoder(Charset.forName("utf-8"));
    }


    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
