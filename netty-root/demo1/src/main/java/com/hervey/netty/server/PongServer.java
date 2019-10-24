package com.hervey.netty.server;

import com.hervey.netty.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @program: netty-root
 * @description: 服务端
 * @author: hewen
 * @create: 2019-08-21 16:35
 **/
public class PongServer {

    public void bind(int port) {
        // 配置服务端的线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            // 只配置一个线程组去操作
            sb.group(bossGroup)
                    .channel(NioServerSocketChannel.class)
                    // 配置tcp参数，配置完成三次握手的连接等待队列
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new PongServerHandler())
                                    .addLast(new PongInboundHandler1())
                                    .addLast(new PongInboundHandler2())
                                    .addLast(new PongInboundHandler3())
                                    .addLast(new PongOutboundHandler1())
                                    .addLast(new PongOutboundHandler2());

                        }
                    });
            // 绑定端口，同步等待成功
            ChannelFuture f = sb.bind(port).sync();

            // 等待服务器监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 9000;
        if (null != args && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采取默认值
            }
        }
        new PongServer().bind(port);
    }
}
