package com.hetangyuese.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;


/**
 * @program: netty-root
 * @description: 通道实例化类
 * @author: hewen
 * @create: 2019-11-09 16:09
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
//                .addLast(new MyServerDecoder())
                .addLast(new MyServerHandler())
        ;
    }
}
