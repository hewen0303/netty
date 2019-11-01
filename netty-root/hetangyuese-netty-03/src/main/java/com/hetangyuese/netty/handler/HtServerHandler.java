package com.hetangyuese.netty.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: netty-root
 * @description: 处理类
 * @author: hetangyuese
 * @create: 2019-10-28 17:39
 **/
@ChannelHandler.Sharable
@Service
public class HtServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Logger log = LoggerFactory.getLogger(HtServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("channel已注册");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (!StringUtils.isEmpty(msg)) {
            log.debug("htServer receive: {}" +  msg);
//            JSONObject object = JSONObject.parseObject(msg);
//            Long userId = object.getLong("userId");
//            if (null == userId) {
//                ctx.writeAndFlush(Unpooled.copiedBuffer("请求参数userId为null：".getBytes(CharsetUtil.UTF_8)));
//            }
//            // 结束当前read传递给下一个handler
//            ctx.fireChannelRead(object);
        }
//        ctx.writeAndFlush(Unpooled.copiedBuffer("请求参数为null：".getBytes(CharsetUtil.UTF_8)));
    }

    /**
     *  服务端接收完毕事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

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
