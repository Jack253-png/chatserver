package com.mcreater.chatserver.client;

import com.mcreater.chatserver.client.handlers.ChatMessageToByteEncoder;
import com.mcreater.chatserver.client.handlers.ClientChatMessageSendHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {
    private String host;
    private Integer port;
    private Bootstrap bootstrap;
    public ChatClient(String host, Integer port) {
        this.host = host;
        this.port = port;
        this.bootstrap = setup();
    }
    private Bootstrap setup() {
        EventLoopGroup group = new NioEventLoopGroup();
        return new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ChatMessageToByteEncoder());
                        pipeline.addLast(new ClientChatMessageSendHandler());
                    }
                });
    }

    public void run() throws InterruptedException {
        bootstrap.connect(host, port)
                .sync()
                .channel()
                .closeFuture()
                .sync();
    }

}