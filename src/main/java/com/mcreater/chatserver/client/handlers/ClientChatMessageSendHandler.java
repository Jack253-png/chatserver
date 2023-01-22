package com.mcreater.chatserver.client.handlers;

import com.mcreater.chatserver.commons.ChatTextMessage;
import com.mcreater.chatserver.commons.TransferPackage;
import com.mcreater.chatserver.commons.TransferPackageWrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientChatMessageSendHandler extends ChannelInboundHandlerAdapter {
    private Channel channel;
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();
        ChatTextMessage message = new ChatTextMessage("Hello!");
        message.setIp(null);
        message.setId("7834875435");
        sendMessage(message);
    }
    public void sendMessage(TransferPackage transferPackage) {
        if (channel != null && channel.isWritable()) {
            TransferPackageWrap transfer = new TransferPackageWrap(transferPackage);
            channel.writeAndFlush(transfer);
        }
    }
}