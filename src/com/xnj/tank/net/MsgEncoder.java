package com.xnj.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Encoder : 编码器
 *      继承于 ： MessageToByteEncoder
 * @author chen xuanyi
 * @create 2020-12-01 13:59
 */
public class MsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf byteBuf) throws Exception {
        //确定是什么类型
        byteBuf.writeInt(msg.getMsgType().ordinal());
        byte[] bytes = msg.toBytes();
        //确定字节数组的长度
        byteBuf.writeInt(bytes.length);
        //发送字节数组
        byteBuf.writeBytes(bytes);
    }
}
