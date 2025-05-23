package com.chat.chatcommon;

import java.io.Serializable;

/**
 * @FileName User
 * @Description
 **/
public class User implements Serializable {

    private String userID;
    private String password;

    public User() {}

    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
