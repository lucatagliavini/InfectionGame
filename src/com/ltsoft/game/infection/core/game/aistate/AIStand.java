package com.ltsoft.game.infection.core.game.aistate;

import com.ltsoft.game.infection.core.AI.AITransition;
import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.states.GameState;

public class AIStand extends AIState {

    private double updateAlive;

    @Override
    public void onUpdate(GameState state, NpcObject npcObject, double deltaTime) {
        this.updateAlive += deltaTime;

    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", ((state, currentCharacter) -> updateAlive >= 1.5));
    }
}
