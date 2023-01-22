package com.mcreater.chatserver.server;

import com.mcreater.chatserver.server.handlers.ByteToChatMessageHandler;
import com.mcreater.chatserver.server.handlers.ServerChatMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Objects;

public class ChatServer {
    private Integer port;
    private ServerBootstrap serverBootstrap;
    public ChatServer(Integer port) {
        this.port = Objects.requireNonNull(port);
        this.serverBootstrap = setup();
    }
    private ServerBootstrap setup() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        return new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ByteToChatMessageHandler());
                        pipeline.addLast(new ServerChatMessageHandler());
                    }
                });
    }
    public void run() throws InterruptedException {
        serverBootstrap.bind(port).sync()
                .channel().closeFuture().sync();
    }
}
