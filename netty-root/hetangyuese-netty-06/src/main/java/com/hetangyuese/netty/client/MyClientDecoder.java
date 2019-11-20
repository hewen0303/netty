package com.hetangyuese.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @program: netty-root
 * @description: 客户端解码器
 * @author: hewen
 * @create: 2019-11-11 09:47
 **/
public class MyClientDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] response = new byte[in.readableBytes()];
        in.readBytes(response);
        String body = new String(response, CharsetUtil.UTF_8);
        out.add(body);
    }
}
