package com.hetangyuese.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: netty-root
 * @description: 客户端逻辑类
 * @author: hewen
 * @create: 2019-10-22 17:15
 **/
public class HtNettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String reqStr = "";
        // 数据缓冲区
        ByteBuf byteBuf = null;
//        reqStr = "hetangyuese" + System.getProperty("line.separator");
        reqStr = "hello!_" +
                "My name is hanleilei !_" +
                "What is your name !_" +
                "How are you? !_"
        ;
        byte[] req = (reqStr).getBytes();
//        for (int i= 0; i<10; i++) {
        byteBuf = Unpooled.buffer(req.length);
        byteBuf.writeBytes(req);
        ctx.writeAndFlush(byteBuf);
//        }
    }
}
