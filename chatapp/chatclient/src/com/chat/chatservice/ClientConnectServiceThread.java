package com.chat.chatservice;

import com.chat.chatcommon.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @FileName ClientConnectServiceThread
 * @Description
 * Каждый объект контактирует с одним клиентом
 **/
public class ClientConnectServiceThread extends Thread {
    private Socket socket;

    public ClientConnectServiceThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Получить "Message" от сервера
     */
    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) in.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
