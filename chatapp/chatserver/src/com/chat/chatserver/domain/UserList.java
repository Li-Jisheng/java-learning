package com.chat.chatserver.domain;

import com.chat.chatcommon.User;

import java.util.HashMap;

/**
 * @FileName UserList
 * @Description
 **/
public class UserList {
    private static HashMap<String, User> users = new HashMap<>();

    static {
        User uid100 = new User("100", "123456");
        users.put("100", uid100);
        User uid200 = new User("200", "123456");
        users.put("200", uid200);
        User uid300 = new User("300", "123456");
        users.put("300", uid300);
    }

    public static User getUserByUID(String uid) {
        return users.get(uid);
    }

    public static void addUser(User user) {
        users.put(user.getUserID(), user);
    }
}
