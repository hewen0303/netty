package com.hetangyuese.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: netty-root
 * @description: 逻辑处理类
 * @author: hewen
 * @create: 2019-11-01 15:49
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger count = new AtomicInteger(1);

    /**
     *  心跳检测机制会进入
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("心跳检测触发了事件, object: , time: " + evt + new Date().toLocaleString());
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            // 客户端连接应该是请求 write
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println("服务端监测到了读取超时");
                count.incrementAndGet();
                if (count.get() > 3) {
                    System.out.println("客户端还在？？ 已经3次检测没有访问了，我要断开了哦！！！");
                    ctx.close();
                }
            } else if (e.state() == IdleState.WRITER_IDLE) {
                // 如果一直有交互则会发送writer_idle
                System.out.println("服务端收到了写入超时");
            } else {
                System.out.println("服务端收到了All_idle");
            }
        } else {
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("myServerHandler is active, time: " + new Date().toLocaleString());
        ctx.writeAndFlush("成功连接服务端, 当前时间：" + new Date().toLocaleString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端与客户端断开了连接, time: " + new Date().toLocaleString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("myServerHandler 收到了客户端的信息 msg:" + msg + ", time: " + new Date().toLocaleString());
        ctx.writeAndFlush("您好，客户端，我是服务端");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
