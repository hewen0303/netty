package com.hetangyuese.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;

/**
 * @program: netty-root
 * @description: 管道初始化类
 * @author: hewen
 * @create: 2019-11-01 15:48
 **/
public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().
                addLast(new StringEncoder(Charset.forName("GBK")))
                .addLast(new StringDecoder(Charset.forName("GBK")))
                .addLast(new LoggingHandler(LogLevel.INFO))
                .addLast(new IdleStateHandler(5, 0, 0))
                .addLast(new MyServerHandler());
    }
}
