package com.ltsoft.game.infection.core;

import javax.swing.*;

public class Launcher {


    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Codice per avviare la frame:
                    GameContainer gameContainer = new GameContainer(800, 600);
                    GameLoop gameLoop = new GameLoop(gameContainer);
                    gameLoop.start();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
