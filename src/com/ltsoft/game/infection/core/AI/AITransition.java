package com.ltsoft.game.infection.core.AI;

import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.states.GameState;

public class AITransition {

    private String nextStateName;
    private AICondition condition;

    public AITransition(String nextStateName, AICondition condition) {
        this.nextStateName = nextStateName;
        this.condition = condition;
    }

    public boolean shouldTransition(AIState state, NpcObject currentCharacter) {
        return this.condition.isMet(state, currentCharacter);
    }

    public String getNextState() {
        return this.nextStateName;
    }
}
