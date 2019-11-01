package com.hetangyuese.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: netty-root
 * @description: 处理类
 * @author: hewen
 * @create: 2019-10-30 15:04
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private static Map<String, Channel> channels = new HashMap<String, Channel>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(("客户端:" + ctx.channel().localAddress().toString() + "已与服务端建立连接， date：" + new Date().toLocaleString()));
        channels.put(ctx.channel().localAddress().toString(), ctx.channel());
        ctx.writeAndFlush(("通知客户端:" + ctx.channel().localAddress().toString() + "已与服务端建立连接， date：" + new Date().toLocaleString()).getBytes(CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(("客户端:" + ctx.channel().localAddress().toString() + "已与服务端断开连接， date：" + new Date().toLocaleString()));
        channels.remove(ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收到消息： msg： " + (String)msg + ", time: " + new Date().toLocaleString());
        if (!channels.isEmpty()) {
            for (Map.Entry<String, Channel> map : channels.entrySet()) {
                map.getValue().writeAndFlush(Unpooled.copiedBuffer(("every client leihou a !\n").getBytes(CharsetUtil.UTF_8)));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static String toAddressString(InetSocketAddress address) {
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }
}
