package com.hetangyuese.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @program: netty-root
 * @description: 继承LengthFileBaseFrameDecoder解码
 * @author: hewen
 * @create: 2019-11-18 15:55
 **/
public class MyServerDecoderLength extends LengthFieldBasedFrameDecoder {

    // 长度为基本类型，占4个字节
    private static int min_head_length = 4;

    /**
     *  构造器初始化LengthFieldBasedFrameDecoder 解码器各参数
     * @param maxFrameLength
     * @param lengthFieldOffset
     * @param lengthFieldLength
     * @param lengthAdjustment
     * @param initialBytesToStrip
     */
    public MyServerDecoderLength(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("decode开始解析啦");
        // 解析的数据总长度
        int size = in.readableBytes();

        if (size < min_head_length) {
            throw new Exception("解析的长度小于长度字段的长度");
        }

        // 获取头部的长度字段
        int bodyLength = in.readInt();
        if (size < bodyLength) {
            throw new Exception("解析的长度小于长度客户端写入的长度");
        }

        ByteBuf body = in.readBytes(bodyLength);
        byte[] bodyArr = new byte[body.readableBytes()];
        body.readBytes(bodyArr);

        return new String(bodyArr);
    }
}
