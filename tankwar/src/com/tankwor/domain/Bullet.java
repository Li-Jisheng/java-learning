package com.tankwor.domain;

import static java.lang.Thread.sleep;

public class Bullet implements Runnable {

    //начальные координаты пули
    private int x;
    private int y;
    //скорость пули
    private int speed = 10;
    //направление пули
    private int direction;
    //повреждение пули
    private int damage = 1;
    //определить, существует ли пуля
    private boolean exist = true;

    /**
     * стрелять
     */
    @Override
    public synchronized void run() {
        while (exist) {

            //определить направление
            switch (direction) {
                case 0:
                    //вверх
                    y -= speed;
                    break;
                case 1:
                    //вниз
                    y += speed;
                    break;
                case 2:
                    //влево
                    x -= speed;
                    break;
                case 3:
                    //вправо
                    x += speed;
                default:
                    break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!exist || x <= 0 || y <= 0 || x >= 1000 || y >= 750) {
                exist = false;
            }
        }
    }

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isExist() {
        return exist;
    }
}
