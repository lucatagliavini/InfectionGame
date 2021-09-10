package com.ltsoft.game.infection.core.game;

import com.ltsoft.game.infection.core.collisions.CollisionBox;
import com.ltsoft.game.infection.core.game.components.PositionComponent;
import com.ltsoft.game.infection.core.game.entity.actions.Action;
import com.ltsoft.game.infection.core.game.entity.effects.Effect;
import com.ltsoft.game.infection.core.graphics.animations.AnimationManager;
import com.ltsoft.game.infection.core.graphics.images.SpriteLibrary;
import com.ltsoft.game.infection.core.inputs.controller.Controller;
import com.ltsoft.game.infection.core.inputs.controller.NpcController;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Direction;
import com.ltsoft.game.infection.core.utils.Motion;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

import javax.swing.text.html.Option;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingObject extends GameObject {

    protected Controller controller;

    protected Motion motion;
    protected Direction direction;

    protected AnimationManager animationManager;

    protected List<Effect> effectList;
    protected Optional<Action> currentAction;

    public MovingObject(String mObjectName, Controller controller) {
        super(mObjectName);
        this.controller = controller;
        this.motion = new Motion(2.0f);
        this.direction = Direction.S;

        this.effectList = new ArrayList<>();
        this.currentAction = Optional.empty();

        this.animationManager = new AnimationManager(
                SpriteLibrary.getUnit("matt"),
                64, 64, 0.7
        );
        this.animationManager.setAnimation("stand");
    }

    @Override
    public void onUpdate(GameState gameState, float deltaTime) {
        this.handleAction(gameState, deltaTime);
        this.handleMotion(deltaTime);

        // Settaggio direzione:
        this.handleCollisions(gameState);
        this.manageDirection();
        this.manageAnimation();
        this.animationManager.onUpdate( deltaTime, this.direction );

        // Effetti:
        this.effectList.forEach( effect -> effect.onUpdate(gameState, this, deltaTime) );

        // Muoviamo alla fine:
        PositionComponent positionComponent = this.getComponent(PositionComponent.class);
        Vector2f position = positionComponent.getPosition();
        position = position.add( this.motion.getVector() );
        // Aggiorniamo il collision BOX:
        this.setPosition( position );

        // Cleanup:
        this.onCleanup();
    }


    /** Gestione delle collisioni dell'oggetto. */
    private void handleCollisions(GameState gameState) {
        List<GameObject> collidingObjects = gameState.getCollidingGameObjects(this);
        collidingObjects.forEach( this::handleCollision );
    }

    public CollisionBox getCollisionBox() {
        Vector2f position = this.getComponent(PositionComponent.class).getPosition();
        Vector2f positionWithMotion = Vector2f.valueOf( position );

        // La Location del Collision Box deve tenere conto dell'OFFSET!!!
        CollisionBox movedCollisionBox = this.collisionBox.copy();
        movedCollisionBox.setLocation( positionWithMotion.add( this.collisionBoxOffset ));
        return movedCollisionBox;
    }

    /** Gestisce la collisione con il singolo oggetto. */
    public abstract void handleCollision( GameObject otherObject );


    public void setAction(Action action) {
        this.currentAction = Optional.of(action);
    }

    public void manageDirection() {
        if( !this.motion.isMoving() ) return;
        this.direction = Direction.setFromMotion( this.motion );
    }

    public void manageAnimation() {
        // Se abbiamo l'azione in corso ha la precedenza:
        if( this.currentAction.isPresent() ) {
            Action action = this.currentAction.get();
            this.animationManager.setAnimation( action.getAnimationName() );
            return;
        }

        // Altrimenti default:
        if( this.motion.isMoving() ) {
            this.animationManager.setAnimation("walk");
        }
        else {
            this.animationManager.setAnimation("stand");
        }
    }

    @Override
    public BufferedImage getSprite() {
        return this.animationManager.getSprite();
    }

    public Controller getController() {
        return this.controller;
    }

    /** Gestione delle azioni. */
    private void handleAction(GameState gameState, double deltaTime) {
        if( !this.currentAction.isPresent() ) return;
        this.currentAction.get().onUpdate( gameState, this, deltaTime );
    }

    /** Gestione movimento. */
    private void handleMotion(float deltaTime) {
        // Non gestiamo movimento con azione in corso:
        if( this.currentAction.isPresent() ) {
            this.motion.stop();
            return;
        }
        this.motion.onUpdate(this.controller, deltaTime);
    }

    /** Eliminiamo gli effetti terminati. */
    private void onCleanup() {
        List.copyOf( this.effectList ).stream()
                .filter( Effect::isFinished )
                .forEach( this.effectList::remove );

        if(this.currentAction.isPresent() && this.currentAction.get().isFinished() ) {
            this.currentAction = Optional.empty();
        }
    }

    public void setSpeedMultiplier(float speedMultiplier) {
        this.motion.multiply( speedMultiplier );
    }

    public void clearEffects() {
        this.effectList.clear();
    }
}
