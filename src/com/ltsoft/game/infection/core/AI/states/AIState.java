package com.ltsoft.game.infection.core.AI.states;

import com.ltsoft.game.infection.core.AI.AITransition;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.states.GameState;

public abstract class AIState {

    protected AITransition transition;

    public AIState() {
        this.transition = this.initializeTransition();
    }

    public abstract void onUpdate(GameState state, NpcObject npcObject, double deltaTime);

    protected abstract AITransition initializeTransition();

    public boolean shouldTransition(AIState state, NpcObject currentCharacter) {
        return this.transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState() {
        return this.transition.getNextState();
    }
}
