package com.hetangyuese.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @program: netty-root
 * @description: 客户端编码类
 * @author: hewen
 * @create: 2019-11-09 16:32
 **/
public class MyClientEncode extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        if (null != msg) {
            byte[] request = msg.getBytes(Charset.forName("UTF-8"));
            out.writeInt(request.length + 1);
            out.writeBytes(request);
        }
    }
}
