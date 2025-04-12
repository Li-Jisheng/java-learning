package com.tankwor.domain;

import com.tankwor.service.Recorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

import static java.lang.Thread.sleep;

/**
 * панель
 */
public class GamePanel extends JPanel implements KeyListener, Runnable {

    public static TankOfPlayer tankOfPlayer = null;
    private Vector<TankOfPlayer> players = new Vector<>();
    public static Vector<TankOfEnemy> tankOfEnemies = new Vector<>();
    private int numOfEnemies = 3;
    public static int killingCount = 0;
    private Vector<Bomb> bombs = new Vector<>();
    private Image bombImage1 = null;
    private Image bombImage2 = null;
    private Image bombImage3 = null;

    public GamePanel() {
        //Recorder.readInfo();

        tankOfPlayer =  new TankOfPlayer(900, 300, 0);
        tankOfPlayer.setTanks(tankOfEnemies);
        //инициализировать объект танка врага
        for (int i = 0; i < numOfEnemies; i++){
            //добавить танк врага
            TankOfEnemy tankOfEnemy = new TankOfEnemy(100 * (i + 1), 100, 1);
            tankOfEnemy.setTankOfPlayer(tankOfPlayer);
            tankOfEnemy.setTanks(tankOfEnemies);
            tankOfEnemies.add(tankOfEnemy);
            new Thread(tankOfEnemy).start();
        }

        players.add(tankOfPlayer);

        //инициализировать объект бомби
        bombImage1 = Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/bomb_1.gif"));
        bombImage2 = Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/bomb_2.gif"));
        bombImage3 = Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/bomb_3.gif"));
    }

    /**
     * создать панель, чтобы рисовать на ней танка и стены
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        //создавать панель
        g.fillRect(0, 0, 1000, 750);

        //рисовать танк игрока
        if (tankOfPlayer.getHeartPoint() > 0) {
            drawTank(g, tankOfPlayer.getX(), tankOfPlayer.getY(),
                    tankOfPlayer.getDirection(), tankOfPlayer.getTypeOfTank());
        }

        //рисовать пуля из танка игрока
        //for (int i = 0; i < tankOfPlayer.bullets.size(); i++) {
        for (Bullet bullet : tankOfPlayer.bullets) {
                drawBullet(g, bullet.getX(), bullet.getY());
        }

        //рисовать танк врага
        for (TankOfEnemy tankOfEnemy : tankOfEnemies) {
            drawTank(g, tankOfEnemy.getX(), tankOfEnemy.getY(),
                    tankOfEnemy.getDirection(), tankOfEnemy.getTypeOfTank());
            for (Bullet bullet : tankOfEnemy.bullets) {
                if (bullet.isExist()) {
                    drawBullet(g, bullet.getX(), bullet.getY());
                }
            }
        }

        //рисовать бомби
        Iterator<Bomb> bombIterator = bombs.iterator();
        while (bombIterator.hasNext()) {
            Bomb bomb = bombIterator.next();

            //рисовать разные картинки в соответствии с разными значениями "life"
            //потом "life" уменьшать
            if (bomb.getLife() > 6){
                g.drawImage(bombImage1, bomb.x - 30, bomb.y - 30, 60, 60, this);
            }
            else if (bomb.getLife() > 3) {
                g.drawImage(bombImage2, bomb.x - 30, bomb.y - 30, 60, 60, this);
            }
            else if (bomb.getLife() > 0) {
                g.drawImage(bombImage3, bomb.x - 30, bomb.y - 30, 60, 60, this);
            }

            bomb.decreaseLife();
            //"life" <= 0, удалить зто объект "Bomb"
            if (bomb.getLife() <= 0) {
                bomb.setDead(true);
                bombIterator.remove();
            }
        }

        showInfo(g);
    }

    public void showInfo(Graphics g) {
        //показать количество убийств
        g.setColor(Color.BLACK);
        Font font = new Font("Times New Roman", Font.BOLD, 30);
        g.setFont(font);
        g.drawString("количество убийств", 1010, 50);
        drawTank(g, 1040, 100, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString("x" + killingCount, 1100, 110);

        //показать "heartPoint"
        g.drawString("HP: " + tankOfPlayer.getHeartPoint(), 1010, 200);
    }

    /**
     * рисовать танк
     * @param g ручка
     * @param x абсцисса
     * @param y ордината
     * @param direction направление танка (0,1,2,3 - вверх, вниз, влево, вправо)
     * @param type тип танка (0 - игрок, 1 - враг)
     */
    public void drawTank(Graphics g, int x, int y, int direction, int type) {
        switch (type) {
            case 0:
                //танк игрока
                g.setColor(Color.cyan);
                break;
            case 1:
                //танк врага
                g.setColor(Color.yellow);
                break;
        }

        //рисовать танк в соответствии с направлением танка
        switch (direction) {
            case 0:
                //вверх
                g.fill3DRect(x - 30, y - 30, 10, 60, false);
                g.fill3DRect(x - 20, y - 20, 40, 40, false);
                g.fillOval(x - 15, y - 15, 30, 30);
                g.fill3DRect(x + 20, y - 30, 10, 60, false);
                g.drawLine(x, y, x, y - 30);
                break;
            case 1:
                //вниз
                g.fill3DRect(x - 30, y - 30, 10, 60, false);
                g.fill3DRect(x - 20, y - 20, 40, 40, false);
                g.fillOval(x - 15, y - 15, 30, 30);
                g.fill3DRect(x + 20, y - 30, 10, 60, false);
                g.drawLine(x, y, x, y + 30);
                break;
            case 2:
                //влево
                g.fill3DRect(x - 30, y - 30, 60, 10, false);
                g.fill3DRect(x - 20, y - 20, 40, 40, false);
                g.fillOval(x - 15, y - 15, 30, 30);
                g.fill3DRect(x - 30, y + 20, 60, 10, false);
                g.drawLine(x, y, x - 30, y);
                break;
            case 3:
                //вправо
                g.fill3DRect(x - 30, y - 30, 60, 10, false);
                g.fill3DRect(x - 20, y - 20, 40, 40, false);
                g.fillOval(x - 15, y - 15, 30, 30);
                g.fill3DRect(x - 30, y + 20, 60, 10, false);
                g.drawLine(x, y, x + 30, y);
                break;
            default:
                break;
        }

    }

    //рисовать пулю
    public void drawBullet(Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.fill3DRect(x - 5, y - 5, 10, 10, false);
    }

    /**
     * Пуля попадет танк. Уничтожить пулю и "heartPoint" танка - 1.
     * Если "heartPoint" танка < 1, уничтожить это танк
     * @param bullets коллекция "bullet" из одного танк
     * @param tanks коллекция "TankOfEnemy" или "TankOfPlayer"
     */
    public void hitTank(Vector<Bullet> bullets, Vector<? extends Tank> tanks) {

        //траверсировать коллекцию "Bullets" и "Tanks"
        Iterator<Bullet> bulletIterator = bullets.iterator();
        Iterator<? extends Tank> tankIterator = tanks.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            while (tankIterator.hasNext()) {
                Tank tank =  tankIterator.next();
                //определить координаты пули и танка
                if (bullet.isExist() &&
                        (bullet.getX() >= tank.getX() - 30 && bullet.getX() <= tank.getX() + 30) &&
                        (bullet.getY() >= tank.getY() - 30 && bullet.getY() <= tank.getY() + 30)) {
                    //если пуля попадет танк
                    //"heartPoint" танка - "damage" пули
                    //удалить пули
                    tank.setHeartPoint(tank.getHeartPoint() - bullet.getDamage());
                    bullet.setExist(false);
                    if (tank.getHeartPoint() <= 0) {
                        bombs.add(new Bomb(tank.getX(), tank.getY()));
                        if (tank.getTypeOfTank() == 1){
                            killingCount++;
                        }
                        tankIterator.remove();

                    }
                }
            }
            if (!bullet.isExist()) {
                bulletIterator.remove();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //изменять координаты танк в соответствии с нажатой клавишем
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            tankOfPlayer.moveUp();
            tankOfPlayer.setDirection(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tankOfPlayer.moveDown();
            tankOfPlayer.setDirection(1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tankOfPlayer.moveLeft();
            tankOfPlayer.setDirection(2);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tankOfPlayer.moveRight();
            tankOfPlayer.setDirection(3);
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            tankOfPlayer.shoot();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Iterator<TankOfEnemy> enemyIterator = tankOfEnemies.iterator();
            while (enemyIterator.hasNext()) {
                TankOfEnemy tankOfEnemy =  enemyIterator.next();
                hitTank(tankOfEnemy.bullets, players);
            }
            hitTank(tankOfPlayer.bullets, tankOfEnemies);
            this.repaint();
        }
    }

    public TankOfPlayer getTankOfPlayer() {
        return tankOfPlayer;
    }

    public void setTankOfPlayer(TankOfPlayer tankOfPlayer) {
        this.tankOfPlayer = tankOfPlayer;
    }

    public Vector<TankOfEnemy> getTankOfEnemies() {
        return tankOfEnemies;
    }

    public void setTankOfEnemies(Vector<TankOfEnemy> tankOfEnemies) {
        this.tankOfEnemies = tankOfEnemies;
    }

    public int getKillingCount() {
        return killingCount;
    }

    public void setKillingCount(int killingCount) {
        this.killingCount = killingCount;
    }
}
