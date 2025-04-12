package com.chat.chatview;

import com.chat.chatservice.UserClientService;
import com.chat.utility.Utility;

/**
 * @FileName Menu
 * @Description
 **/
public class Menu {

    public void selectMenu() {
        String userID = "";
        String password;
        boolean loop = true; //интерфейс входа управления
        boolean signIn = false; //Определить, был ли вход в систему успешным или нет
        int key;
        UserClientService userClientService = new UserClientService();

        while (loop) {
            showSignInMenu();
            //читать информацию, введенную с клавиатуры
            key = Utility.readInt(1);
            switch (key) {
                //вход
                case 1:
                    System.out.println("введите имя пользователя и пароль");
                    userID = Utility.readString(10);
                    password = Utility.readString(12);
                    signIn = userClientService.signIn(userID, password);
                    if (signIn) {
                        loop = false;
                    }
                    else
                        System.out.println("Неправильное имя пользователя или пароль");
                    break;
                //выход
                case 0:
                    System.out.println("Выход");
                    return;
            }
        }


        while (signIn) {
            showUserMenu(userID);
            //Чтение клавиатурного ввода
            key = Utility.readInt(1);
            switch (key) {
                case 1:
                    System.out.println("======= Показать список пользователей онлайн =======");
                    break;
                case 2:
                    System.out.println("=================== групповой чат ==================");
                    break;
                case 3:
                    System.out.println("=================== приватный чат ==================");
                    break;
                case 4:
                    System.out.println("================= Отправить документ ===============");
                    break;
                case 0:
                    System.out.println("Выход");
                    return;
            }
        }
    }

    /**
     * Показать экран входа
     */
    public void showSignInMenu() {
        System.out.println("============войти=============");
        System.out.println("\t\t 1. вход");
        System.out.println("\t\t 0. выход");
    }

    /**
     * Показать пользовательское меню
     * @param userID
     */
    public void showUserMenu(String userID) {
        System.out.println("============Здравствуйте, " + userID + "=============");
        System.out.println("\t 1. Показать список пользователей онлайн");
        System.out.println("\t 2. групповой чат");
        System.out.println("\t 3. приватный чат");
        System.out.println("\t 4. Отправить документ");
        System.out.println("\t 0. exit");
    }
}
