package com.mcreater.chatserver.commons;

import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.mcreater.chatserver.util.JsonUtil.GSON;

public class ChatTextMessage implements TransferPackage {
    private String message;
    private String ip;
    private String id;
    public ChatTextMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMessage(String message) {
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
