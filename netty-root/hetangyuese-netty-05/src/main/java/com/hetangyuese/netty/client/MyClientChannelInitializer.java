package com.hetangyuese.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;


/**
 * @program: netty-root
 * @description: 通道初始化
 * @author: hewen
 * @create: 2019-11-01 17:17
 **/
public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")))
                .addLast(new StringDecoder(Charset.forName("GBK")))
                .addLast(new MyClientChannelHandler());
    }
}
