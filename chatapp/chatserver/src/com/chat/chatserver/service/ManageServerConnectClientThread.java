package com.chat.chatserver.service;

import java.util.HashMap;

/**
 * @FileName ManageServerConnectClientThread
 * @Description
 **/
public class ManageServerConnectClientThread {
    public static HashMap<String, ServerConnectClientThread> collection = new HashMap<>();

    public static void addTread(String userID, ServerConnectClientThread thread) {
        collection.put(userID, thread);
    }

    /**
     * Получить поток по "userID"
     * @param userID
     * @return
     */
    public static ServerConnectClientThread getTread(String userID) {
        return collection.get(userID);
    }
}
