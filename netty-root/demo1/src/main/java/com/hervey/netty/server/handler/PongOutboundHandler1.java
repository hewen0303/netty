package com.hervey.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @program: netty-root
 * @description:
 * @author: hewen
 * @create: 2019-10-16 17:05
 **/
public class PongOutboundHandler1 extends ChannelOutboundHandlerAdapter{

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
    }
}
