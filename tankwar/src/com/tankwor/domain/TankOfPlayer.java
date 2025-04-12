package com.tankwor.domain;

//танк игрока
public class TankOfPlayer extends Tank{

    private int typeOfTank = 0;

    public TankOfPlayer(int x, int y, int direction) {
        super(x, y, direction);
    }

    @Override
    public int getTypeOfTank() {
        return typeOfTank;
    }

    @Override
    public void setTypeOfTank(int typeOfTank) {
        this.typeOfTank = typeOfTank;
    }
}

