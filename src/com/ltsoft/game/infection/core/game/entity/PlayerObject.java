package com.ltsoft.game.infection.core.game.entity;

import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.game.MovingObject;
import com.ltsoft.game.infection.core.game.entity.effects.Caffeinated;
import com.ltsoft.game.infection.core.inputs.controller.Controller;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerObject extends MovingObject {


    public PlayerObject(Controller controller) {
        super(controller);
        this.animationManager.setAnimation("stand");
        // Effetto di SpeedBoost:
        //this.effectList.add(new Caffeinated());
    }

    @Override
    public void onUpdate(GameState gameState, double deltaTime) {
        super.onUpdate(gameState, deltaTime);
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        if(otherObject instanceof NpcObject npcObject) {
            npcObject.clearEffects();
        }
    }
}
