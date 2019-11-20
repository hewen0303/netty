package com.hetangyuese.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @program: netty-root
 * @description: 客户端
 * @author: hewen
 * @create: 2019-11-09 15:59
 **/
public class MyClient06 {

    private final String ip = "127.0.0.1";
    private final int port = 9002;

    private void run() {
        // 工作线程组
        EventLoopGroup work = new NioEventLoopGroup(4);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .option(ChannelOption.TCP_NODELAY, false)
                    .handler(new MyClientChannelInitializer());
            System.out.println("客户端启动，准备连接服务端.....................");
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port)).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new MyClient06().run();
    }
}
