package com.ltsoft.game.infection.core.graphics;

import com.ltsoft.game.infection.core.GameContainer;
import com.ltsoft.game.infection.core.GameSettings;
import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.inputs.Input;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import java.util.List;

public class Display extends JFrame {

    private Size size;
    private Input input;
    private Canvas canvas;

    private Renderer renderer;
    private DebugRenderer debugRenderer;

    public Display(int width, int height) {
        this.size = new Size(width, height);
        this.setTitle("InfectionGame - v0.9a");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.renderer = new Renderer();
        this.debugRenderer = new DebugRenderer();
        this.input = new Input();
        this.addKeyListener( this.input );

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setFocusable(false);
        this.canvas.setIgnoreRepaint(true);
        this.add(canvas);
        this.pack();

        this.canvas.createBufferStrategy(2);

        this.setLocationRelativeTo(null);
        this.setVisible( true );
    }


    public void onRender(GameState gameState, GameSettings gameSettings) {
        BufferStrategy bs = this.canvas.getBufferStrategy();
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

        // Rendering:
        this.renderer.onRender(gameState, g2d);

        // Rendering debug:
        if( gameSettings.isDebugModeEnabled() ) {
            this.debugRenderer.onRender( gameState, g2d );
        }

        g2d.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }

    /** Prelevo l'input listener. */
    public Input getInput() {
        return this.input;
    }

    public Size getDisplaySize() {
        return this.size;
    }


    public static void main(String[] args) {
        Display display = new Display(800, 600);
    }
}
