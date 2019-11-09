package com.hetangyuese.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;

/**
 * @program: netty-root
 * @description: ceshi
 * @author: hewen
 * @create: 2019-11-01 15:42
 **/
public class MyServer05 {

    private ServerBootstrap serverBootstrap = null;

    public static void main(String[] arg) {
        MyServer05 myServer05 = new MyServer05();
        myServer05.start(9001);
    }

    public void start(int port) {

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();

        try {
          serverBootstrap = getServerBootstrap();
          serverBootstrap.group(boss, work)
                  .option(ChannelOption.SO_REUSEADDR, true)
                  .option(ChannelOption.SO_BACKLOG, 1024)
                  .childOption(ChannelOption.SO_KEEPALIVE, true)
                  .channel(NioServerSocketChannel.class)
                  .childHandler(new MyServerChannelInitializer());

          System.out.println("myServer is start time: " + new Date().toLocaleString());

          ChannelFuture future = serverBootstrap.bind(port).sync();
          future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

    private ServerBootstrap getServerBootstrap() {
        if (null == serverBootstrap) {
            serverBootstrap = new ServerBootstrap();
        }
        return serverBootstrap;
    }
}
