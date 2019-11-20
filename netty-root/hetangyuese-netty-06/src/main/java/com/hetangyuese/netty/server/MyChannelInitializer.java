package com.hetangyuese.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
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
//                .addLast(new MyServerDecoderLength(10240, 0, 4, 0, 0))
                .addLast(new LengthFieldBasedFrameDecoder(10240, 0, 4, 0, 4))
//                .addLast(new MyServerDecoder())
                .addLast(new MyServerHandler())
        ;
    }
}
