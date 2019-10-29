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
 * @author: hetangyuese
 * @create: 2019-10-28 17:28
 **/
@Component
public class HtServer {

    private Logger log = LoggerFactory.getLogger(HtServer.class);

    /**
     * Netty服务端启动类
     */
    private ServerBootstrap serverBootstrap;

    /**
     *  服务通道
     */
    @Autowired
    private HtServerChannel htServerChannel;

    /**
     * Netty日志处理类，可以打印出入站出站的日志，方便排查
     * 需搭配 channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
     * 使用
     */
    private ChannelHandler logging = new LoggingHandler();

    /**
     *
     * @param port 启动端口号
     */
    public void start(int port) {
        log.debug("htServer start port:{}", port);
        // 主线程组 用于处理连接
        EventLoopGroup boss = new NioEventLoopGroup(1);
        // 工作线程组用于处理业务逻辑
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            serverBootstrap = getServerBootstrap();
            // 配置服务端启动类
            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .handler(logging)
                    .childHandler(htServerChannel);

            // 服务端绑定端口并持续等待
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 通道持续阻塞等待直到关闭了服务
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            // 输出错误日志
            log.error("netty server start happened exception e:{}", e);
        } finally {
            // 关闭线程组
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    /**
     *  初始化启动类
     * @return
     */
    public ServerBootstrap getServerBootstrap() {
        if (null == serverBootstrap) {
            serverBootstrap = new ServerBootstrap();
        }
        return serverBootstrap;
    }
}
