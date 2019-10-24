package com.hervey.netty.server.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @program: netty-root
 * @description:
 * @author: hewen
 * @create: 2019-10-17 18:12
 **/
public class PongServiceDecoder1 extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 假使消息为int字节 则需要判断bytebuf字节是否大于等于4
        if (in.readableBytes() >= 4) {
            out.add(in.readInt());
        }
    }
}
