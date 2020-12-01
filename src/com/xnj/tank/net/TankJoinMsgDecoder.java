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
public class TankJoinMsgDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //解决TCP 的拆包和粘包问题，当byteBuf 不满 8 个时，不处理
        if (byteBuf.readableBytes() < 33){
            return;
        }

        TankJoinMsg msg = new TankJoinMsg();

        msg.x = byteBuf.readInt();
        msg.y = byteBuf.readInt();
        msg.dir = Dir.values()[byteBuf.readInt()];
        msg.moving = byteBuf.readBoolean();
        msg.group = Group.values()[byteBuf.readInt()];
        msg.id = new UUID(byteBuf.readLong(), byteBuf.readLong());

        list.add(msg);

    }
}
