package com.tankwor.service;

import com.tankwor.domain.GamePanel;
import com.tankwor.domain.TankOfEnemy;
import com.tankwor.domain.TankOfPlayer;

import java.io.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * @FileName Recorder
 * @Description Сохранять и получить информацию об игре в локальном файле
 **/
public class Recorder {

    private static int enemies = 0;
    private static ObjectOutputStream objectOutputStream = null;
    private static ObjectInputStream objectInputStream = null;
    private static String recordFile = "out/production/tankwar/gameRecord.txt";
    private static int kills;
    private static TankOfPlayer player = null;
    private static Vector<TankOfEnemy> enemyTanks = new Vector<>();

    /**
     * Сохранять и получить информацию
     */
    public static void recordInfo() {
        enemies = GamePanel.tankOfEnemies.size();

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(recordFile));
            objectOutputStream.writeInt(GamePanel.killingCount);
            objectOutputStream.writeObject(GamePanel.tankOfPlayer);
            objectOutputStream.writeObject(enemies);
            Iterator<TankOfEnemy> enemiesIterator = GamePanel.tankOfEnemies.iterator();
            while (enemiesIterator.hasNext()) {
                TankOfEnemy tankOfEnemy = enemiesIterator.next();
                objectOutputStream.writeObject(tankOfEnemy);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Получить информацию
     */
    public static void readInfo () {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(recordFile));
            kills = objectInputStream.readInt();
            player = (TankOfPlayer)objectInputStream.readObject();
            enemies = objectInputStream.readInt();
            enemyTanks = (Vector<TankOfEnemy>)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
