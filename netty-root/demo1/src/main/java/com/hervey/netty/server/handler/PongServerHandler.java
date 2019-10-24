package com.hervey.netty.server.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @program: netty-root
 * @description: 消费端处理类
 * @author: hewen
 * @create: 2019-08-21 16:58
 **/
public class PongServerHandler extends SimpleChannelInboundHandler{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("receive from client:" + o.toString());
        channelHandlerContext.fireChannelRead(o);
        if (o.toString().equals("ping")) {
            channelHandlerContext.writeAndFlush("收到");
        } else {
            channelHandlerContext.writeAndFlush("熊猫头熊猫头,没流量了，over over...");
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.writeAndFlush(Unpooled.copiedBuffer(new String("123").toCharArray(), CharsetUtil.UTF_8));
        ctx.fireChannelReadComplete();
    }
}
