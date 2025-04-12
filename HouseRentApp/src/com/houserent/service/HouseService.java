package com.houserent.service;

import com.houserent.domain.House;

import java.util.Scanner;

import static com.houserent.utility.Utility.readConfirmSelection;
import static com.houserent.utility.Utility.readNumber;
import static com.houserent.utility.Utility.readString;

/**
 * Определить массив, "house[]", сохранить объект "house"
 * Выполнять различные операции с информацией о доме
 */
public class HouseService {

    private House[] houses = new House[10];
    private int count = 0;

    public HouseService() {}

    /**
     * пока
     */
    public void showHousesList() {

        System.out.println("серийный номер\t\tтеле\tадресс\tцена\tстатус");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.println(i + "\t\t" + houses[i].toString());
        }
    }

    public boolean addHouse() {
        //массив заполнен
        if (count == houses.length) {
            return false;
        }

        //читать информацию, введенную пользователем
        Scanner scanner = new Scanner(System.in);
        System.out.println("Входить имя владельца:");
        String owner = readString(20, "owner");
        System.out.println("Входить адрес:");
        String address = readString(100);
        System.out.println("Входить номер телефона:");
        String tel = readNumber(11);

        //Определить "арендовано" или "праздно" на основании введенного значения "Y/N"
        String status;
        System.out.println("Выбрать арендовано или праздно(Y/N)");
        char statusSelection = readConfirmSelection();
        if (statusSelection == 'Y') {
            status = "арендовано";
        }
        else {
            status = "праздно";
        }

        //Определить, является ли входная цена разумной
        System.out.println("Входить цену:");
        double price = 0.0d;
        while (scanner.hasNextLine()) {
            price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("Входите правильный номер");
                continue;
            }
            break;
        }

        //создать новый класс "House", и передать параметры
        houses[count] = new House(owner, address, price, tel, status);
        count++;
        return true;
    }
}
