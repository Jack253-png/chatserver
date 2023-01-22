package com.mcreater.chatserver;

import com.mcreater.chatserver.client.ChatClient;
import com.mcreater.chatserver.server.ChatServer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) System.exit(1);
        if (args[0].equals("server")) {
            ChatServer server = new ChatServer(Integer.parseInt(args[1]));
            server.run();
        }
        else if (args[0].equals("client")) {
            ChatClient client = new ChatClient("localhost", Integer.parseInt(args[1]));
            client.run();
        }
        else {
            System.exit(1);
        }
    }
}