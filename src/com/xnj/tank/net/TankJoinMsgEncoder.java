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
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMsg tankMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(tankMsg.toBytes());
    }
}
