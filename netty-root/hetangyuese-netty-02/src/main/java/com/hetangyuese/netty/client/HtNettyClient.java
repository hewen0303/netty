package com.hetangyuese.netty.client;

import com.hetangyuese.netty.client.handler.HtNettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @program: netty-root
 * @description: 客户端
 * @author: hewen
 * @create: 2019-10-22 17:15
 **/
public class HtNettyClient {

    private String ip;

    private int port;

    HtNettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(ip, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HtNettyClientHandler());
                }
            });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HtNettyClient client = new HtNettyClient("127.0.0.1", 9000);
        client.start();
    }
}
