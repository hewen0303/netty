package com.hetangyuese.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;

/**
 * @program: netty-root
 * @description:
 * @author: hewen
 * @create: 2019-11-23 10:26
 **/
public class TestDirectByteBuf {


    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.directBuffer(2);
        byteBuf.writeByte(88);
        byteBuf.capacity(200);
    }
}
