package com.ltsoft.game.infection.core.inputs.controller;

import com.ltsoft.game.infection.core.utils.Position;

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

    public void moveToTarget(Position currentPosition, Position targetPosition) {
        double dx = targetPosition.getPosition().x - currentPosition.getPosition().x;
        double dy = targetPosition.getPosition().y - currentPosition.getPosition().y;

        this.UP = (dy < 0) && Math.abs( dy ) > Position.PROXYMITY_RANGE;
        this.RIGHT = (dx > 0) && Math.abs( dx ) > Position.PROXYMITY_RANGE;
        this.LEFT = (dx < 0) && Math.abs( dx ) > Position.PROXYMITY_RANGE;
        this.DOWN = (dy > 0) && Math.abs( dy ) > Position.PROXYMITY_RANGE;
    }
}
