package com.hetangyuese.netty.server;

import com.hetangyuese.netty.server.channel.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: netty-root
 * @description: 测试
 * @author: hewen
 * @create: 2019-10-30 14:50
 **/
public class MyServer {


    public void start(int port) {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = null;
        try {

            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, work)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("服务端启动了....端口：" + port);
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.start(9000);
    }
}
