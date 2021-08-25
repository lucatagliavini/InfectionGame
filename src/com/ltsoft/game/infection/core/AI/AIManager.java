package com.ltsoft.game.infection.core.AI;

import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.aistate.AIStand;
import com.ltsoft.game.infection.core.game.aistate.AIWander;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.states.GameState;

import java.awt.event.WindowAdapter;

public class AIManager {

    private AIState currentState;

    public AIManager() {
        this.transitionTo("stand");
    }


    public void onUpdate(GameState state, NpcObject npcObject, double deltaTime) {
        this.currentState.onUpdate(state, npcObject, deltaTime);

        if( this.currentState.shouldTransition(this.currentState, npcObject)) {
            this.transitionTo(this.currentState.getNextState());
        }
    }


    private void transitionTo( String nextState ) {
        //System.out.println("AIManager> Transition to: " + nextState);

        switch(nextState) {
            case "wander":
                this.currentState = new AIWander();
                return;
            case "stand":
            default:
                this.currentState = new AIStand();
        }
    }
}
