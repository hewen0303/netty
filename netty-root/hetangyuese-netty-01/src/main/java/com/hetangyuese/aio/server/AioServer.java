package com.hetangyuese.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @program: netty-root
 * @description: aio服务端
 * @author: hewen
 * @create: 2019-10-12 16:05
 **/
public class AioServer extends Thread {

    private AsynchronousServerSocketChannel serverSocketChannel;

    final int port = 9999;

    @Override
    public void run() {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel
                    .open(AsynchronousChannelGroup
                            .withCachedThreadPool(Executors.newCachedThreadPool(), 10));
            serverSocketChannel.bind(new InetSocketAddress(port));

            CountDownLatch latch = new CountDownLatch(1);
            serverSocketChannel.accept();
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }
}
