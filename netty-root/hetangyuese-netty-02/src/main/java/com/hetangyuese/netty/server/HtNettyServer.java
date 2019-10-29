package com.hetangyuese.netty.server;

import com.hetangyuese.netty.server.handler.HtNettyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @program: netty-root
 * @description: netty服务端
 * @author: hewen
 * @create: 2019-10-22 16:47
 **/
public class HtNettyServer {

    private int port;

    HtNettyServer(int port) {
        this.port = port;
    }

    public void start() {
        // 定义一个boss来处理请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    // 设置等待连接的数量
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
//                                    .addLast(new FixedLengthFrameDecoder(3))
                                    .addLast(new DelimiterBasedFrameDecoder(1024, false, Unpooled.copiedBuffer("!_".getBytes())))
                                    .addLast(new HtNettyHandler());
                        }
                    });
            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 阻塞至直至关闭通道
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HtNettyServer server = new HtNettyServer(9000);
        server.start();
    }
}
