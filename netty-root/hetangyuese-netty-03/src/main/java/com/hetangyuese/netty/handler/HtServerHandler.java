package com.hetangyuese.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @program: netty-root
 * @description: 处理类
 * @author: hetangyuese
 * @create: 2019-10-28 17:39
 **/
@ChannelHandler.Sharable
@Service
public class HtServerHandler extends ChannelInboundHandlerAdapter {

    private Logger log = LoggerFactory.getLogger(HtServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("channel已注册");
        ctx.writeAndFlush(Unpooled.copiedBuffer("xixixix".getBytes()));
    }

    /**
     *  服务端接收到的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("htServer receive" + (String)msg);
    }

    /**
     *  服务端接收完毕事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Htserver readComplete".getBytes()));
    }

    /**
     *  异常捕获事件
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
