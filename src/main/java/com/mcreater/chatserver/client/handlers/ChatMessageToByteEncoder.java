package com.mcreater.chatserver.client.handlers;

import com.mcreater.chatserver.commons.TransferPackageWrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ChatMessageToByteEncoder extends MessageToByteEncoder<TransferPackageWrap> {
    protected void encode(ChannelHandlerContext ctx, TransferPackageWrap msg, ByteBuf out) {
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getBytes());
    }
}
