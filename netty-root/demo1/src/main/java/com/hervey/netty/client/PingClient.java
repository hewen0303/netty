package com.hervey.netty.client;

import com.hervey.netty.client.handler.PingClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @program: netty-root
 * @description: 监听ip地址客户端
 * @author: hewen
 * @create: 2019-08-21 12:01
 **/
public class PingClient {

    private String host = "127.0.0.1";

    private Integer port = 9000;

    private EventLoopGroup group = null;

    private Bootstrap bootstrap = null;

    private Channel channel = null;

    public PingClient() {
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();

        bootstrap.group(group)
        .option(ChannelOption.TCP_NODELAY, true)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new StringDecoder())
                        .addLast(new StringEncoder())
                        .addLast(new PingClientHandler());
            }
        });
    }

    public Channel getChannel() throws InterruptedException {
        // 单例 如果不存在或者没生存（生命周期）
        if (null == channel || !channel.isActive()) {
            channel = bootstrap.connect(host, port).sync().channel();
        }
        return channel;
    }

    public void stop() {
        if (null != group) {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        PingClient client = new PingClient();
        Channel channel = null;
        try {
            channel = client.getChannel();
            while(true) {
                System.out.println(channel.isOpen());
                if (channel.isActive()) {
                    channel.writeAndFlush("蘑菇头蘑菇头，收到请回答哦...");
                } else {
                    System.out.println("失去连接，关闭客户端");
                    channel.close();
                    break;
                }
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != client) {
                client.stop();
            }
        }


    }
}
