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
 * @create: 2019-11-01 17:17
 **/
public class MyClient05 {

    private Bootstrap bootstrap = null;

    private String ip;

    private int port;

    MyClient05(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }


    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap = getBootstrap();

            bootstrap.group(group)
                    .option(ChannelOption.AUTO_READ, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInitializer());

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port));
            future.addListener(new MyChannelFutureListener());
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    private Bootstrap getBootstrap() {
        if (null == bootstrap) {
            bootstrap = new Bootstrap();
        }
        return bootstrap;
    }

    protected static void reConnection() {
        new MyClient05("192.168.0.118", 9001).start();
    }

    public static void main(String[] args) {
        MyClient05 myClient05 = new MyClient05("192.168.0.118", 9001);

        myClient05.start();
    }

}
