package com.chat.chatserver.service;

import com.chat.chatcommon.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @FileName ServerConnectClientThread
 * @Description
 **/
public class ServerConnectClientThread extends Thread {
    Socket socket;
    String userID; //Имя пользователя, установившего соединение с сервером


    public ServerConnectClientThread(Socket socket, String userID) {
        this.socket = socket;
        this.userID = userID;
    }

    /**
     * Получить "Message" от клиента
     */
    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
