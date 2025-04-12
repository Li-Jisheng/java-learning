package com.tankwor.view;

import com.tankwor.domain.GamePanel;
import com.tankwor.service.Recorder;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWar extends JFrame {

    GamePanel gamePanel = null;

    public static void main(String[] args) {
        TankWar tankWar = new TankWar();
    }

    public
    TankWar() {
        gamePanel = new GamePanel();
        new Thread(gamePanel).start();
        this.add(gamePanel);
        this.setSize(1300, 800);
        this.addKeyListener(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Recorder.recordInfo();
                System.exit(0);
            }
        });
    }
}
