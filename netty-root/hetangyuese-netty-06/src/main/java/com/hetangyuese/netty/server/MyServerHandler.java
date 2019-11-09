package com.hetangyuese.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @program: netty-root
 * @description: 服务处理逻辑类
 * @author: hewen
 * @create: 2019-11-09 16:10
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接成功 time: " + new Date().toLocaleString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接 time: " + new Date().toLocaleString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果为设置解码器为String的话，需要通过bytebuf接收来处理
        ByteBuf byteBuf = (ByteBuf) msg;
        // 定义一个数组接收
        byte[] request = new byte[byteBuf.readableBytes()];
        // 数据写入byte数组
        byteBuf.readBytes(request);
        String body = new String(request, CharsetUtil.UTF_8);
        System.out.println("收到客户端发来的消息:" + body +  ", time: " + new Date().toLocaleString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常关闭通道
        cause.printStackTrace();
        ctx.close();
    }
}
