package com.xnj.tank.net;

import com.xnj.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author chen xuanyi
 * @create 2020-11-30 16:35
 */
public class Client {
    //保存 channel，一个客户端连接到服务器，存在一个channel
    private Channel channel = null;

    private Client() {
    }
    private static class ClientLoader {
        private static final Client INSTANCE = new Client();
    }
    public static Client getInstance() {
        return ClientLoader.INSTANCE;
    }

    public  void connect() {
        EventLoopGroup group = new NioEventLoopGroup(2);

        Bootstrap bs = new Bootstrap();

        try {
            ChannelFuture cf = bs.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pl = socketChannel.pipeline();
                            pl.addLast(new MsgEncoder())
                                    .addLast(new MsgDecoder())
                                    .addLast(new ClientHandler());
                        }
                    })
                    .connect("localhost",9898)
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                System.out.println("connected");
                                channel = channelFuture.channel();//初始化，保存channel
                            }else {
                                System.out.println("not connected");
                            }
                        }
                    }).sync();
            System.out.println("waiting");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void send(String msg) {
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    public void send(Msg msg){
//        ByteBuf buf = Unpooled.copiedBuffer(msg.toBytes());
        channel.writeAndFlush(msg);
    }

    public void closeConnect() {
        this.send("_bye_");
        //channel.close();

    }
}

//class ClientHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = null;
//        try {
//            buf = (ByteBuf)msg;
//            byte[] bytes = new byte[buf.readableBytes()];
//            buf.getBytes(buf.readerIndex(), bytes);
//            String msgAccepted = new String(bytes);
////            System.out.println(new String(bytes));
//        }finally {
//            if (buf != null) {
//                ReferenceCountUtil.release(buf);
//            }
//        }
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(new TankJoinMsg(5, 8));
//    }
//}

class ClientHandler extends SimpleChannelInboundHandler<Msg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        msg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(new TankJoinMsg(TankFrame.getInstance().getMainTank()));

    }
}