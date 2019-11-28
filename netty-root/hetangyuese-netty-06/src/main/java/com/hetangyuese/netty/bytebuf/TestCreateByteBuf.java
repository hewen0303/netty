package com.hetangyuese.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @program: netty-root
 * @description: byteBuf测试类
 * @author: hewen
 * @create: 2019-11-20 15:58
 **/
public class TestCreateByteBuf {


    public static void main(String[] args) {
        // 利用非池化Unpooled类创建字节缓冲区
        ByteBuf byteBuf = Unpooled.buffer(2);
        byteBuf.capacity(100);
        System.out.println("initCapacity: " + byteBuf.capacity());

        byteBuf.writeByte(66);
        byteBuf.writeByte(67);
        byteBuf.readBytes(1);
        System.out.println("readerIndex: " + byteBuf.readerIndex());
        System.out.println("writerIndex: " + byteBuf.writerIndex());

        // 丢弃已经阅读的字节
        byteBuf.discardReadBytes();
        byteBuf.writeByte(68);
        byteBuf.writeByte(69);
        System.out.println("readerIndex: " + byteBuf.readerIndex());
        System.out.println("writerIndex: " + byteBuf.writerIndex());
        System.out.println("capacity: " + byteBuf.capacity());
        int newCapacity = 128;
        System.out.println(newCapacity <<= 1);
    }
}
