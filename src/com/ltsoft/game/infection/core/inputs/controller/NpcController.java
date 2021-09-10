package com.ltsoft.game.infection.core.inputs.controller;

import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class NpcController implements Controller {

    private boolean UP;
    private boolean DOWN;
    private boolean LEFT;
    private boolean RIGHT;

    public NpcController() {
        this.stop();
    }

    @Override
    public boolean isRequestingUp() {
        return this.UP;
    }

    @Override
    public boolean isRequestingDown() {
        return this.DOWN;
    }

    @Override
    public boolean isRequestingLeft() {
        return this.LEFT;
    }

    @Override
    public boolean isRequestingRight() {
        return this.RIGHT;
    }

    public void stop() {
        this.UP = false;
        this.DOWN = false;
        this.LEFT = false;
        this.RIGHT = false;
    }

    public void moveToTarget(Vector2f currentPosition, Vector2f targetPosition) {
        double dx = targetPosition.x - currentPosition.x;
        double dy = targetPosition.y - currentPosition.y;

        this.UP = (dy < 0) && Math.abs( dy ) > Position.PROXYMITY_RANGE;
        this.RIGHT = (dx > 0) && Math.abs( dx ) > Position.PROXYMITY_RANGE;
        this.LEFT = (dx < 0) && Math.abs( dx ) > Position.PROXYMITY_RANGE;
        this.DOWN = (dy > 0) && Math.abs( dy ) > Position.PROXYMITY_RANGE;
    }
}
