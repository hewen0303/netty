package com.hetangyuese.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @program: netty-root
 * @description: 服务端
 * @author: hewen
 * @create: 2019-11-09 15:59
 **/
public class MyServer06 {

    private final int port = 9002;


    private void start() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        //  默认CPU核数*2，如果已知业务最大线程数，最好初始化的时候指定
        EventLoopGroup work = new NioEventLoopGroup(4);

        ServerBootstrap serverBootstrap = null;
        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childHandler(new MyChannelInitializer());
            System.out.println("MyServer06 is start ...................");

            ChannelFuture future = serverBootstrap.bind(port).sync();
            // 通道关闭了也阻塞执行下去
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new MyServer06().start();
    }
}
