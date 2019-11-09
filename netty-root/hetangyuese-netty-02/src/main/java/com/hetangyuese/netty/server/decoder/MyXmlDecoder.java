package com.hetangyuese.netty.server.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @program: netty-root
 * @description: xml解析器
 * @author: hewen
 * @create: 2019-11-07 11:57
 **/
public class MyXmlDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("123123");
        byte[] params = new byte[in.readableBytes()];
        in.readBytes(params);
        String body = new String(params, CharsetUtil.UTF_8);
        System.out.println(body);
    }
}
