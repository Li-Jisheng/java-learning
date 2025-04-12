package com.chat.chatservice;

import com.chat.chatcommon.Message;
import com.chat.chatcommon.MessageType;
import com.chat.chatcommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @FileName UserClientService
 * @Description
 * Реализовать функции, такие как вход и регистрация пользователей
 **/
public class UserClientService {
    private User user = new User();
    private Socket socket;

    public boolean signIn(String userID, String password) {
        user.setUserID(userID);
        user.setPassword(password);
        boolean signIn = false;

        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            //создать объект "ObjectOutputStream", чтобы отправить сообщение на сервер
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);

            //читать объект "", отправленный сервера.
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) objectInputStream.readObject();

            //Если вход в систему прошел успешно,
            //запустить поток для получения информации от сервера
            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                ClientConnectServiceThread connect = new ClientConnectServiceThread(socket);
                connect.start();
                ManageClientConnectServiceThread.addThread(userID, connect);
                signIn = true;
            }
            else {
                socket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signIn;
    }
}
