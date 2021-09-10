package com.ltsoft.game.infection.core.game.aistate;

import com.ltsoft.game.infection.core.AI.AITransition;
import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.inputs.controller.NpcController;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class AIWander extends AIState {

    private List<Vector2f> targets;

    public AIWander() {
        super();
        this.targets = new ArrayList<>();
    }

    @Override
    public void onUpdate(GameState gameState, NpcObject npcObject, double deltaTime) {
        if( this.targets.isEmpty() ) {
            this.targets.add( gameState.getRandomPosition() );
        }

        Vector2f targetPosition = this.targets.get(0);
        NpcController controller = (NpcController) npcObject.getController();
        controller.moveToTarget( npcObject.getPosition(), targetPosition );

        if( this.isArrived( npcObject ) ) {
            controller.stop();
        }
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentNpc) -> this.isArrived(currentNpc)));
    }

    private boolean isArrived(NpcObject npcObject) {
        Vector2f currentPosition = npcObject.getPosition();
        Vector2f targetPosition = this.targets.get(0);
        return Position.isInRangeOf( currentPosition, targetPosition, Position.PROXYMITY_RANGE + 10.0 );
    }
}
