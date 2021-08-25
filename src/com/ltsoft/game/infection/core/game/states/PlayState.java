package com.ltsoft.game.infection.core.game.states;

import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.game.entity.PlayerObject;
import com.ltsoft.game.infection.core.game.entity.actions.Cough;
import com.ltsoft.game.infection.core.graphics.Camera;
import com.ltsoft.game.infection.core.graphics.images.SpriteLibrary;
import com.ltsoft.game.infection.core.inputs.Input;
import com.ltsoft.game.infection.core.inputs.controller.PlayerController;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.Size;
import com.ltsoft.game.infection.core.world.Tile;
import com.ltsoft.game.infection.core.world.TiledMap;

public class PlayState extends GameState {

    public PlayState(Size windowSize, Input input) {
        super(windowSize, input);
    }


    public void initializeCharacters() {
        // Inizializzazione mappa:
        this.tiledMap = new TiledMap(new Size(20, 10));

        // Inizializzo:
        PlayerObject playerObject = new PlayerObject(new PlayerController(this.input));
        this.gameObjectList.add( playerObject );
        this.camera.setFocusOnObject( playerObject );

        this.initializeNPCS( 100 );
    }


    public void initializeNPCS(int number) {
        for(int i=0; i<number; i++) {
            NpcObject npc = new NpcObject();

            Position randomPosition = this.getRandomPosition();
            npc.setPosition(new Position( randomPosition.getPosition().x, randomPosition.getPosition().y ));
            npc.setAction(new Cough());
            this.gameObjectList.add( npc );
        }
    }
}
