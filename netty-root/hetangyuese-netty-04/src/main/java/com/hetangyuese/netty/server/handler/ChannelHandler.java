package com.hetangyuese.netty.server.handler;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: netty-root
 * @description: 通道管理类
 * @author: hewen
 * @create: 2019-10-30 15:19
 **/
public class ChannelHandler {

    /**
     * 用于存储活跃的channel连接
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
