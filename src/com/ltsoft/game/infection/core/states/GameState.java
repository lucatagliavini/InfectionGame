package com.ltsoft.game.infection.core.states;

import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.graphics.Camera;
import com.ltsoft.game.infection.core.graphics.images.SpriteLibrary;
import com.ltsoft.game.infection.core.inputs.Input;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.Size;
import com.ltsoft.game.infection.core.utils.math.Vector2f;
import com.ltsoft.game.infection.core.world.TiledMap;

import java.util.*;
import java.util.stream.Collectors;

public abstract class GameState {

    // GameMap:
    protected TiledMap tiledMap;

    // Concetti dello stato:
    protected Map<String, List<GameObject>> mGameObjectsMap;
    protected List<GameObject> gameObjectList;
    protected Input input;

    // Camera:
    protected Camera camera;


    public GameState(Size windowSize, Input input) {
        this.input = input;
        this.gameObjectList = new ArrayList<>();
        this.mGameObjectsMap = new HashMap<>();
        this.camera = new Camera(windowSize);
        // Inizializzazione:
        SpriteLibrary.loadSprites();
        this.onInit();
    }


    /** Aggiungiamo un Game Object. */
    public void addGameObject(GameObject gameObject) {
        String mObjectName = gameObject.getName();
        List<GameObject> otherObjects = this.mGameObjectsMap.get( mObjectName );
        if( otherObjects == null ) {
            otherObjects = new ArrayList<>();
            this.mGameObjectsMap.put( mObjectName, otherObjects );
        }
        otherObjects.add( gameObject );

        // Aggiungo all'array:
        this.gameObjectList.add( gameObject );
    }


    /** Prelevo l'oggetto dal nome. */
    public List<GameObject> getObjectsByName(String mObjectName) {
        return this.mGameObjectsMap.get( mObjectName );
    }

    /** Inizializzazione. */
    public void onInit() {
        this.initializeCharacters();
    }

    public void initializeCharacters() {

    }


    public void onUpdate(float deltaTime) {
        // Aggiorniamo tutti i game objects:
        this.gameObjectList.forEach( gameObject -> gameObject.onUpdate(this, deltaTime) );
        this.sortObjectsByPosition();

        // Update Camera:
        this.camera.onUpdate(this, deltaTime);
    }

    /** Applichiamo il Y-Ordering: */
    private void sortObjectsByPosition() {
        this.gameObjectList.sort(
                Comparator.comparing( gameObject -> gameObject.getPosition().y)
        );
    }

    /** Per usi esterni. */
    public List<GameObject> getGameObjects() {
        return this.gameObjectList;
    }

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }

    public Camera getCamera() {
        return this.camera;
    }


    public Vector2f getRandomPosition() {
        float x = (float)Math.random() * (float)this.tiledMap.getMapWitdh();
        float y = (float)Math.random() * (float)this.tiledMap.getMapHeight();
        return new Vector2f(x, y);
    }


    /** Resistuisco tutti i game objects colliding con questo. */
    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        // Creiamo una lista dalla lista di partenza:
        return this.gameObjectList.stream()
                .filter( other -> other.collidingWith( gameObject ))
                .collect(Collectors.toList());
    }
}
