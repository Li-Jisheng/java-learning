package com.chat.chatservice;

import java.util.HashMap;

/**
 * @FileName ManageClientConnectServiceThread
 * @Description
 * Использовать для управления потоками "ClientConnectServiceThread"
 **/
public class ManageClientConnectServiceThread {
    public static HashMap<String, ClientConnectServiceThread> collection = new HashMap<>();

    /**
     * Добавить поток
     * @param userID
     * @param thread
     */
    public static void addThread(String userID, ClientConnectServiceThread thread) {
        collection.put(userID, thread);
    }

    /**
     * Получить поток по "userID"
     * @param userID
     * @return
     */
    public static ClientConnectServiceThread getThread(String userID) {
        return collection.get(userID);
    }
}
