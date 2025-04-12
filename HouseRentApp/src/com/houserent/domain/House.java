package com.houserent.domain;

/**
 * класс "house" означает информацию о доме
 */
public class House {
    //владелец, адрес, цена, номер телефона и статус
    private String owner;
    private String address;
    private double price;
    private String tel;
    private String status;

    //конструктор, getter, setter
    public House(String owner, String address, double price, String tel, String status) {
        this.owner = owner;
        this.address = address;
        this.price = price;
        this.tel = tel;
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public String getTel() {
        return tel;
    }

    public String getStatus() {
        return status;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //печатать информанцию
    @Override
    public String toString() {
        return owner + "\t" + tel + "\t" + address + "\t" + price + "\t" + status;
    }
}
