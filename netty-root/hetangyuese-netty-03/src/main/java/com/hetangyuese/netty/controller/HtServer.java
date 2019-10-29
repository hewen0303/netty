package com.hetangyuese.netty.controller;

import com.hetangyuese.netty.channel.HtServerChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: netty-root
 * @description: ht服务类
 * @author: hewen
 * @create: 2019-10-28 17:28
 **/
@Component
public class HtServer {

    private Logger log = LoggerFactory.getLogger(HtServer.class);

    private ServerBootstrap serverBootstrap;

    @Autowired
    private HtServerChannel htServerChannel;

    private ChannelHandler logging = new LoggingHandler();

    public void start(int port) {
        System.out.println("start post = " + port);
        log.debug("htServer start port:{}", port);
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            serverBootstrap = getServerBootstrap();

            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .handler(logging)
                    .childHandler(htServerChannel);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public ServerBootstrap getServerBootstrap() {
        if (null == serverBootstrap) {
            serverBootstrap = new ServerBootstrap();
        }
        return serverBootstrap;
    }
}
