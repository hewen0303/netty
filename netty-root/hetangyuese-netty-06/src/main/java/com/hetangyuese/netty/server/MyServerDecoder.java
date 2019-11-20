package com.hetangyuese.netty.server;

import com.hetangyuese.netty.client.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @program: netty-root
 * @description: 解码器
 * @author: hewen
 * @create: 2019-11-09 16:11
 **/
public class MyServerDecoder extends ByteToMessageDecoder {


    private static int min_head_length = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 解码的字节长度
        int size = in.readableBytes();
        if(size < min_head_length) {
            System.out.println("解析的数据长度小于头部长度字段的长度");
            return ;
        }
        // 读取的时候指针已经移位
        int length = in.readInt();
        if (size < length) {
            System.out.println("解析的数据长度与长度不符合");
            return ;
        }

        // 上面已经读取到了长度字段，后面的长度就是body
        ByteBuf decoderArr = in.readBytes(length);
        byte[] request = new byte[decoderArr.readableBytes()];
        // 将数据写入空数组
        decoderArr.readBytes(request);
        String body = new String(request, Charset.forName("UTF-8"));
        out.add(body);
    }
}
