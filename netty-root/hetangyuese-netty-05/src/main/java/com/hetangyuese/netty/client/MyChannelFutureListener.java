package com.hetangyuese.netty.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @program: netty-root
 * @description: 异步通知类
 * @author: hewen
 * @create: 2019-11-06 16:22
 **/
public class MyChannelFutureListener implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            System.out.println("当前已连接");
            return;
        }
        System.out.println("启动连接客户端失败，开始重连");
        final EventLoop loop = future.channel().eventLoop();
        loop.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                   MyClient05.reConnection();
                   System.out.println("客户端重连成功");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 1L, TimeUnit.SECONDS);
    }
}
