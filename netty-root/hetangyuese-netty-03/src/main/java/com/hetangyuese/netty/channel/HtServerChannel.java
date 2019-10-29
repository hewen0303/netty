package com.hetangyuese.netty.channel;

import com.hetangyuese.netty.handler.HtServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: netty-root
 * @description: 配置管道
 * @author: hewen
 * @create: 2019-10-28 17:35
 **/
@Service
public class HtServerChannel extends ChannelInitializer {

    @Autowired
    private HtServerHandler htServerHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new StringDecoder());
        channelPipeline.addLast(htServerHandler);
    }
}
