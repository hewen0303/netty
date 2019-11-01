package com.hetangyuese.netty.server.channel;

import com.hetangyuese.netty.server.handler.MyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;


/**
 * @program: netty-root
 * @description: 通道类
 * @author: hewen
 * @create: 2019-10-30 15:03
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 消息体最长1024要有换行符没有则报错
        ch.pipeline()
                .addLast(new StringDecoder(CharsetUtil.UTF_8))
                .addLast(new StringDecoder(Charset.forName("UTF-8")))
                .addLast(new MyServerHandler());
    }
}
