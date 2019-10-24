package com.hetangyuese.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @program: netty-root
 * @description: 服务端处理类
 * @author: hewen
 * @create: 2019-10-22 16:47
 **/
public class HtNettyHandler extends ChannelInboundHandlerAdapter {


    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果没有定义解码器的话 默认转byteBuf
        ByteBuf byteBuf = (ByteBuf)msg;
        byte[] params = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(params);
        String body = new String(params, CharsetUtil.UTF_8);
        System.out.println("收到了请求，请求内容：" + body + ", 收到请求次数：" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
