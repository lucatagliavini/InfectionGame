package com.ltsoft.game.infection.core.game.aistate;

import com.ltsoft.game.infection.core.AI.AITransition;
import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.inputs.controller.NpcController;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class AIWander extends AIState {

    private List<Position> targets;

    public AIWander() {
        super();
        this.targets = new ArrayList<>();
    }

    @Override
    public void onUpdate(GameState gameState, NpcObject npcObject, double deltaTime) {
        if( this.targets.isEmpty() ) {
            this.targets.add( gameState.getRandomPosition() );
        }

        Position target = this.targets.get(0);
        NpcController controller = (NpcController) npcObject.getController();
        controller.moveToTarget( npcObject.getPosition(), target );

        if( this.isArrived( npcObject ) ) {
            controller.stop();
        }
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentNpc) -> this.isArrived(currentNpc)));
    }

    private boolean isArrived(NpcObject npcObject) {
        Position targetPosition = this.targets.get(0);
        Position npcPosition = npcObject.getPosition();
        return npcPosition.isInRangeOf( targetPosition, Position.PROXYMITY_RANGE + 10.0 );
    }
}
