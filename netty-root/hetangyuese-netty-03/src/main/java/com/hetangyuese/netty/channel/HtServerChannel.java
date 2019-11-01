package com.hetangyuese.netty.channel;

import com.hetangyuese.netty.decord.HtServerDecoder;
import com.hetangyuese.netty.handler.HtServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: netty-root
 * @description: 配置管道
 * @author: hetangyuese
 * @create: 2019-10-28 17:35
 **/
@Service
public class HtServerChannel extends ChannelInitializer {

    @Autowired
    private HtServerHandler htServerHandler;

    @Autowired
    private HtServerDecoder htServerDecoder;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        // 通道流水线 管理channelHandler的有序执行
        ChannelPipeline channelPipeline = ch.pipeline();
        // netty日志
        channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
        // 字符串解码器 接收到数据直接转为string 这里没有弄自定义和其他的解码器
        channelPipeline.addLast(new StringDecoder());
        // 业务逻辑处理类
        channelPipeline.addLast(htServerHandler);
    }
}
