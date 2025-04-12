package com.tankwor.domain;

public class Bomb {

    public int x;
    public int y;
    private int life = 9;
    private boolean dead = false;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void decreaseLife() {
        this.life--;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
