package com.ltsoft.game.infection.core;

public class GameSettings {

    private boolean isDebugModeEnabled;


    public GameSettings(boolean isDebugModeEnabled) {
        this.isDebugModeEnabled = isDebugModeEnabled;
    }


    public boolean isDebugModeEnabled() {
        return this.isDebugModeEnabled;
    }
}
