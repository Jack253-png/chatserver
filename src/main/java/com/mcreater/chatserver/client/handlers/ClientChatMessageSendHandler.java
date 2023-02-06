package com.mcreater.chatserver.client.handlers;

import com.mcreater.chatserver.commons.ChatTextMessage;
import com.mcreater.chatserver.commons.TransferPackage;
import com.mcreater.chatserver.commons.TransferPackageWrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ClientChatMessageSendHandler extends ChannelInboundHandlerAdapter {
    private Channel channel;
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();
        JTextArea area = new JTextArea();
        JButton button = new JButton("send");

        button.addActionListener(e -> {
            ChatTextMessage message = new ChatTextMessage(area.getText());
            message.setIp(null);
            message.setId("7834875435");
            sendMessage(message);
        });

        JFrame frame = new JFrame("Chat client demo");
        frame.setLayout(new FlowLayout());
        frame.add(area);
        frame.add(button);
        frame.setVisible(true);
    }
    public void sendMessage(TransferPackage transferPackage) {
        if (channel != null && channel.isWritable()) {
            TransferPackageWrap transfer = new TransferPackageWrap(transferPackage);
            channel.writeAndFlush(transfer);
        }
    }
}