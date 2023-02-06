package com.mcreater.chatserver.commons;

import io.netty.util.CharsetUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.mcreater.chatserver.util.JsonUtil.GSON;

@Data
public class ChatTextMessage implements TransferPackage {
    private String message;
    private String ip;
    private String id;
    public ChatTextMessage(String message) {
        this.message = message;
    }

    public byte[] toJsonMessage() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("ip", ip);
        data.put("message", message);
        return GSON.toJson(data).getBytes(CharsetUtil.UTF_8);
    }
}
