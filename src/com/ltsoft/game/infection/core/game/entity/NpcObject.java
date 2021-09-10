package com.ltsoft.game.infection.core.game.entity;

import com.ltsoft.game.infection.core.AI.AIManager;
import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.game.MovingObject;
import com.ltsoft.game.infection.core.game.aistate.AIStand;
import com.ltsoft.game.infection.core.graphics.animations.AnimationManager;
import com.ltsoft.game.infection.core.graphics.images.SpriteLibrary;
import com.ltsoft.game.infection.core.inputs.controller.Controller;
import com.ltsoft.game.infection.core.inputs.controller.NpcController;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Direction;
import com.ltsoft.game.infection.core.utils.Motion;

import java.util.function.Function;

public class NpcObject extends MovingObject {

    private AIManager npcManager;

    public NpcObject(String mObjectName) {
        super(mObjectName, new NpcController());
        this.npcManager = new AIManager();

        this.motion = new Motion(2.0f);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(
                SpriteLibrary.getUnit("dave"),
                64, 64, 0.7
        );
        this.animationManager.setAnimation("stand");
    }

    @Override
    public void onUpdate(GameState gameState, float deltaTime) {
        super.onUpdate(gameState, deltaTime);
        this.npcManager.onUpdate(gameState, this, deltaTime);
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        if( otherObject instanceof PlayerObject ) {
            this.motion.stop();
        }
    }
}
