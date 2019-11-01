package com.hetangyuese.netty.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: netty-root
 * @description: 业务逻辑类
 * @author: hewen
 * @create: 2019-10-30 11:08
 **/
@ChannelHandler.Sharable
public class HtServerBusinessHandler extends SimpleChannelInboundHandler {

    private Logger log = LoggerFactory.getLogger(HtServerBusinessHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("HtServerBusinessHandler 接收到数据 msg:{}", msg);
        JSONObject obj = new JSONObject();

        obj.put("userId", 123);
        obj.put("userName", "张三");
        obj.put("age", 23);
        obj.put("sex", "男");
        ctx.writeAndFlush(Unpooled.copiedBuffer(obj.toString().getBytes(CharsetUtil.UTF_8)));
    }
}
