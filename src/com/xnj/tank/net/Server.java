package com.xnj.tank.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @author chen xuanyi
 * @create 2020-11-30 16:14
 */
public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

//    public static void main(String[] args) {
//        Server.start();
//    }

    public static void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        ServerBootstrap b = new ServerBootstrap();

        try {
            ChannelFuture cf = b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pl = socketChannel.pipeline();
                            pl.addLast(new MsgEncoder())
                                    .addLast(new MsgDecoder())
                                    .addLast(new ServerChildHandler());
                        }
                    })
                    .bind(9898)
                    .sync();

            ServerFrame.INSTANCE.updateServerMsg("server start");

            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Server.clients.writeAndFlush(msg);
        ServerFrame.INSTANCE.updateClientMsg(msg.toString());
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.getBytes(buf.readerIndex(), bytes);
////        System.out.println(new String(bytes));
//        String s = new String(bytes);
//
//        ServerFrame.INSTANCE.updateClientMsg(s);
//
//        //判断是否退出
//        if (s.equals("_bye_")){
//            ServerFrame.INSTANCE.updateServerMsg("客户端要求退出");
//            Server.clients.remove(ctx.channel());
//            ctx.close();
//        }else {
//            Server.clients.writeAndFlush(msg);
//        }
//        Server.clients.writeAndFlush(msg );
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
