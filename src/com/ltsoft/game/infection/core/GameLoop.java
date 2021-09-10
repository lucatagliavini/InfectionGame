package com.ltsoft.game.infection.core;

public class GameLoop implements Runnable {

    // Il Game su cui chiamare i metodi:
    private GameContainer gameContainer;

    // Thread del game loop:
    private Thread thread;
    private volatile boolean isRunning;

    private final float upsRate = 60.0f;
    private final float updateRate = 1.0f / upsRate;
    private final float nanoToSeconds = 1.0f / 1e9f;

    // Gestione statistiche:
    private boolean statsEnabled = true;
    private final float statsTime = 1.0f; // Ogni secondo
    private double elapsedStatsTime;
    private int fpsCount, upsCount;

    private final int MAX_FPS_COUNTABLE = 10000;
    private final int MAX_UPS_COUNTABLE = 10000;


    public GameLoop(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }


    /** Faccio partire il thread. */
    public synchronized void start() {
        if(this.isRunning) return;
        this.thread = new Thread(this);
        this.thread.start();
    }


    /** Implementazione del game loop */
    @Override
    public void run() {
        this.isRunning = true;
        this.fpsCount = 0;
        this.upsCount = 0;
        this.elapsedStatsTime = 0.0f;

        // Contatori per il loop:
        float accumulatedTime = 0.0f;
        float elapsedTime = 0.0f;
        long currTime, lastTime = System.nanoTime();
        boolean mustRender = false;
        // Inizializzazione:
        this.gameContainer.onInit();
        while( this.isRunning ) {
            mustRender = true;
            currTime = System.nanoTime();
            elapsedTime = (currTime - lastTime) * nanoToSeconds;
            accumulatedTime += elapsedTime;
            elapsedStatsTime += elapsedTime;
            lastTime = currTime;

            // Update Loop:
            while( accumulatedTime >= this.updateRate) {
                this.onUpdate();
                accumulatedTime -= this.updateRate;
                mustRender = true;
            }

            // Rendering:
            if( mustRender ) {
                this.onRender();
            }
            // Aspetto:
            if( accumulatedTime > 0 ) this.sleep(1);

            // Stampiamo le stats:
            if(this.statsEnabled) this.printStats();
        }
    }

    private void printStats() {
        if( this.elapsedStatsTime < this.statsTime ) return;
        System.out.println(String.format("UPS: %d - FPS: %d", this.upsCount, this.fpsCount));
        this.upsCount = 0;
        this.fpsCount = 0;
        this.elapsedStatsTime = 0.0d;
    }


    public void onUpdate() {
        this.gameContainer.onUpdate(this.updateRate);
        // Limito in modo da non avere overflow.
        this.upsCount = (this.upsCount+1) % MAX_UPS_COUNTABLE;
    }


    public void onRender() {
        this.gameContainer.onRender();
        // Limito in modo da non avere overflow.
        this.fpsCount = (this.fpsCount+1) % MAX_FPS_COUNTABLE;
    }


    private void sleep(long millis) {
        try {
            Thread.sleep( millis );
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
