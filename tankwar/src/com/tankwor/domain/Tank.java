package com.tankwor.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

/**
 * класс Tank
 */
public class Tank implements Serializable {

    //координата танка
    private int x;
    private int y;
    //направление танка (0,1,2,3 - вверх, вниз, влево, вправо)
    private int direction;
    //скорость танка
    private int speed = 5;
    //HP танка
    private int heartPoint = 5;
    //тип танка
    private int typeOfTank;
    //пуля
    public Vector<Bullet> bullets = new Vector<>();
    //получить информацию о других танках
    private Vector<? extends Tank> tanks = new Vector<>();

    /**
     * создать пулю и стрелять
     * Когда программа вызывает метод «shoot»,
     * в коллекцию «bullets» добавляется объект «bullet» и запускается его поток.
     */
    public void shoot() {
        Bullet bullet = new Bullet(x, y, direction);
        bullets.add(bullet);
        new Thread(bullet).start();
    }

    //танк двигаться вверх/вниз/влево/вправо
    public void moveUp() {
        if (y - 30 >= 10 && !touchOtherTank())
            y -= speed;
    }
    public void moveDown() {
        if (y + 30 < 750 && !touchOtherTank())
            y += speed;
    }
    public void moveLeft() {
        if (x - 30 >= 10 && !touchOtherTank())
            x -= speed;
    }
    public void moveRight() {
        if (x + 30 < 1000 && !touchOtherTank())
            x += speed;
    }

    /**
     * Сравнить координаты танков, чтобы определить, соприкасаются ли танки друг с другом
     * @return Если соприкасаются ли танки друг с другом, return true
     */
    public boolean touchOtherTank() {
        boolean touched = false;
        Iterator<? extends Tank> tanksIterator = tanks.iterator();
        while (tanksIterator.hasNext()) {
            Tank tank = tanksIterator.next();
            if (this == tank) {
                continue;
            }

            switch (getDirection()) {
                case 0:
                    if (this.getX() - tank.getX() < 60 && this.getX() - tank.getX() > -60
                            && this.getY() - tank.getY() <= 60
                            && this.getY() - tank.getY() >= 0) {
                        touched = true;
                    }
                case 1:
                    if (this.getX() - tank.getX() < 60 && this.getX() - tank.getX() > -60
                            && this.getY() - tank.getY() >= -60
                            && this.getY() - tank.getY() <= 0) {
                        touched = true;
                    }
                case 2:
                    if (this.getY() - tank.getY() < 60 && this.getY() - tank.getY() > -60
                            && this.getX() - tank.getX() <= 60
                            && this.getX() - tank.getX() >= 0) {
                        touched = true;
                    }
                case 3:
                    if (this.getY() - tank.getY() < 60 && this.getY() - tank.getY() > -60
                            && this.getX() - tank.getX() <= -60
                            && this.getX() - tank.getX() <= 0) {
                        touched = true;
                    }
            }
        }
        return touched;
    }

    public Tank(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getHeartPoint() {
        return heartPoint;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTypeOfTank() {
        return typeOfTank;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setHeartPoint(int heartPoint) {
        this.heartPoint = heartPoint;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setTypeOfTank(int typeOfTank) {
        this.typeOfTank = typeOfTank;
    }

    public void setTanks(Vector<? extends Tank> tanks) {
        this.tanks = tanks;
    }
}
