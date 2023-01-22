package com.mcreater.chatserver.commons;

import io.netty.util.CharsetUtil;

public class TransferPackageWrap {
    private final TransferPackage transferPackage;
    public TransferPackageWrap(TransferPackage transferPackage) {
        this.transferPackage = transferPackage;
    }
    public int getLength() {
        return transferPackage.toJsonMessage().length;
    }
    public byte[] getBytes() {
        return transferPackage.toJsonMessage();
    }
}
