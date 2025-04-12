package com.chat.chatserver.service;

import com.chat.chatcommon.Message;
import com.chat.chatcommon.MessageType;
import com.chat.chatcommon.User;
import com.chat.chatserver.domain.UserList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @FileName chatserver
 * @Description
 **/
public class ChatServer {

    ServerSocket serverSocket;

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(9999);

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                //читать объект "User" от клиента
                User user = (User) objectInputStream.readObject();

                //Получить "ObjectOutputStream", связанный с "socket"
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                //использоваться для ответа клиент
                Message message = new Message();

                //Определить правильность имени пользователя
                //и пароля и отправьте соответствующее сообщение
                if (checkUser(user.getUserID(), user.getPassword())) {
                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCESS);
                    objectOutputStream.writeObject(message);
                    //запустить поток для получения информации от сервера
                    ServerConnectClientThread connect
                            = new ServerConnectClientThread(socket, user.getUserID());
                    connect.start();
                    ManageServerConnectClientThread.addTread(user.getUserID(), connect);
                }
                else {
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAILURE);
                    objectOutputStream.writeObject(message);
                    socket.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Если прекратить "while", сервер больше не прослушивает клиент,
            // закрыть "ServerSocket".
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean checkUser(String userID, String password) {
        if (UserList.getUserByUID(userID) == null) {
            return false;
        }
        else {
            if (UserList.getUserByUID(userID).getPassword().equals(password)) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
