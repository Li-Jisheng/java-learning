package com.houserent.view;

import com.houserent.service.HouseService;

import static com.houserent.utility.Utility.readConfirmSelection;
import static com.houserent.utility.Utility.readMenuSelection;

/**
 * Показывать главное меню.
 * Выполнять соответствующие задачи в соответствии с входными данными.
 */
public class MainMenu {

    public void selectMenu() {
        boolean loop = true; //управлять отображением меню
        HouseService houseService = new HouseService();
        do {
            showMenu();
            char selection = readMenuSelection(); //получить информонцию о выборе пользователя
            switch (selection) {
                case '1':
                    System.out.println("Добавить дом");
                    houseService.addHouse();
                    System.out.println("Успех");
                    break;
                case '2':
                    System.out.println("Удалить дом");
                    //deletHouse();
                    break;
                case '3':
                    System.out.println("Найти дом");
                    //searchHouse();
                    break;
                case '4':
                    System.out.println("Изменить информанцию домов");
                    //changeHouse();
                    break;
                case '5':
                    System.out.println("Показывать список домов");
                    houseService.showHousesList();
                    break;
                case '6':
                    //Спросить, хотеть ли выйти
                    System.out.println("Вы хотите ли выйти?");
                    char judgment = readConfirmSelection();
                    if (judgment == 'N') {
                        continue;
                    }
                    //Выйти приложение
                    System.out.println("Выход");
                    loop = false;
                    break;
                default:
                    System.out.println("Неверный ввод данных.");
            }
        } while (loop);
    }

    //Показывать главное меню.
    public void showMenu() {
        System.out.println("\n===========================Меню===========================");
        System.out.println("\t\t\t\t1.Добавить дом");
        System.out.println("\t\t\t\t2.Удалить дом");
        System.out.println("\t\t\t\t3.Найти дом");
        System.out.println("\t\t\t\t4.Изменить информанцию домов");
        System.out.println("\t\t\t\t5.Показывать меню домов");
        System.out.println("\t\t\t\t6.Выход");
    }
}
