package io.pgs.svc.pref.event;


import java.util.function.Consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public final class NettyStartupUtil {
    public static void runServer(int port, ChannelHandler childHandler, Consumer<ServerBootstrap> block) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            b.handler(new LoggingHandler(LogLevel.INFO));
            b.childHandler(childHandler);
            block.accept(b);
            Channel ch = b.bind(port).sync().channel();
            System.err.println("Ready for 0.0.0.0:" + port);
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void runServer(int port, ChannelHandler childHandler) throws Exception {
        runServer(port, childHandler, b -> {});
    }

    public static void runServer(int port, Consumer<ChannelPipeline> initializer) throws Exception {
        runServer(port, new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                initializer.accept(ch.pipeline());
            }
        });
    }

    public static void runClient(String host, int port, ChannelHandler handler) throws Exception {
        runClient(host, port, handler, b -> {});
    }

    public static void runClient(String host, int port, Consumer<ChannelPipeline> initializer) throws Exception {
        runClient(host, port, new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                initializer.accept(ch.pipeline());
            }
        });
    }

    public static void runClient(String host, int port, ChannelHandler handler, Consumer<Bootstrap> block) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class);
            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000); // 연결 타임아웃 3초
            b.handler(new LoggingHandler(LogLevel.DEBUG));
            b.handler(handler);
            block.accept(b);
            try {
                Channel ch = b.connect(host, port).sync().channel();
                System.err.println("[Connect for: /" + host + ":" + port + "]");
                ch.closeFuture().sync();
            } catch (Exception e) {
                System.err.println("[" + e.getLocalizedMessage() + "]");
            }

        } finally {
            group.shutdownGracefully();
        }
    }
}