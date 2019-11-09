package com.hetangyuese.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: netty-root
 * @description: 处理类
 * @author: hewen
 * @create: 2019-11-01 17:18
 **/
public class MyClientChannelHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端建立了连接 time: " + new Date().toLocaleString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开了连接, time: " + new Date().toLocaleString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyClient05("127.0.0.1", 9001).start();
                    System.out.println("客户端重新连接了服务端 time：" + new Date().toLocaleString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到了服务的响应的数据 msg: " + msg + ", time: " + new Date().toLocaleString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
