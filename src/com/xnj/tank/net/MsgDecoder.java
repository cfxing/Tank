package com.xnj.tank.net;

import com.xnj.tank.Dir;
import com.xnj.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * Decoder : 解码器
 * @author chen xuanyi
 * @create 2020-12-01 14:01
 */
public class MsgDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //消息头和字节长度为 8 byte
        if (byteBuf.readableBytes() < 8){
            return;
        }

        //读到哪 做标记
        byteBuf.markReaderIndex();

        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();

        if (byteBuf.readableBytes() < length) {
            //重置读指针到mark记录的位置
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Msg msg = null;
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMsg();
                msg.parse(bytes);
                list.add(msg);
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                msg.parse(bytes);
                list.add(msg);
            default:
                break;
        }

    }
}
