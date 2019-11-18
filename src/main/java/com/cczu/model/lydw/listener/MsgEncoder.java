package com.cczu.model.lydw.listener;

import com.cczu.model.lydw.entity.BluetoothLocation;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class MsgEncoder  extends ProtocolEncoderAdapter {
    private final Charset charset;
    public MsgEncoder(Charset charset) {
        this.charset = charset;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(MsgEncoder.class);

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

    }
}
