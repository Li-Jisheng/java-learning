package com.tankwor.domain;

public class TankOfEnemy extends Tank implements Runnable{

    private int typeOfTank = 1;
    //контролировать дистанцию автомотического перемещения
    private int step = 20;
    private int stepCounter = 0;
    //контролировать частота стрельби
    private int shootCount = 0;
    private TankOfPlayer tankOfPlayer;

    @Override
    public void run() {
        while(getHeartPoint() > 0){
            //танк стреляет каждые 5 раз, когда перемещается
            if (shootCount < 5) {
                shootCount++;
            }
            else if (shootCount == 5) {
                shoot();
                shootCount = 0;
            }

            autoMove();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //танк целится и стреляет автомтически
    //если танк игрока в пределах досягаемости стрельбы, танк врага стреляет.
    @Override
    public void shoot() {

        if (tankOfPlayer.getX() >= this.getX() - 30
                && tankOfPlayer.getX() <= this.getX() + 30) {
            //изменять направление, чтобы быть лицом к цели
            //потом стрелять
            if (tankOfPlayer.getY() >= this.getY()) {
                setDirection(1);
                super.shoot();
            }
            else {
                setDirection(0);
                super.shoot();
            }
        }
        else if (tankOfPlayer.getY() >= this.getY() - 30
                && tankOfPlayer.getY() <= this.getY() + 30) {
            //изменять направление
            if (tankOfPlayer.getX() >= this.getX()) {
                setDirection(3);
                super.shoot();
            }
            else {
                setDirection(2);
                super.shoot();
            }
        }
    }

    //танк перемещается автомтически
    private void autoMove() {
        //сделать "step" шагов в одном ноправлении
        //потом случайным образом менять нопровление
        if (stepCounter == step) {
            setDirection((int)(Math.random() * 4));
            stepCounter = 0;
            //случайным образом задать количество шагов
            setStepCounter((int)(Math.random() * 20));
        }

        switch (getDirection()) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
            default:
                return;
        }
        stepCounter++;
    }

    public TankOfEnemy(int x, int y, int direction) {
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

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    public void setTankOfPlayer(TankOfPlayer tankOfPlayer) {
        this.tankOfPlayer = tankOfPlayer;
    }
}
