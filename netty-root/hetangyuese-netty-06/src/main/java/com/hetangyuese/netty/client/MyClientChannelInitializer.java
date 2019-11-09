package com.hetangyuese.netty.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @program: netty-root
 * @description: 通道初始化类
 * @author: hewen
 * @create: 2019-11-09 16:29
 **/
public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
//                .addLast(new MyClientEncode())
                .addLast(new MyClientHandler())
        ;

    }
}
