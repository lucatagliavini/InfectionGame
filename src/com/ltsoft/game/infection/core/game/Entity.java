package com.ltsoft.game.infection.core.game;

import com.ltsoft.game.infection.core.game.components.EntityComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entity {

    // Nome della entity:
    private String mEntityName;

    // Lista dei componenti della entity:
    private List<EntityComponent> mComponentList;


    /** Creazione di una entity vuota. */
    public Entity(String mEntityName) {
        this.mEntityName = mEntityName;
        this.mComponentList = new ArrayList<>();
    }


    /** Aggiungo un componente alla entity. */
    public void addComponent(EntityComponent c) {
        c.gameObject = this;
        this.mComponentList.add( c );
    }


    /** Prelevo un componente castandolo. */
    public <T extends EntityComponent> T getComponent(Class<T> componentClass) {
        Iterator<EntityComponent> componentIterator = this.mComponentList.iterator();
        while(componentIterator.hasNext()) {
            EntityComponent component = componentIterator.next();
            if( component.getClass().isAssignableFrom( componentClass ) ) {
                return componentClass.cast( component );
            }
        }
        return null;
    }


    /** Metodo di update di tutti i component. */
    public void onUpdate(float fDeltaTime) {
        this.mComponentList.stream()
                .forEach(c -> c.onUpdate(fDeltaTime));
    }


    /** Restituisco la entity name. */
    public String getName() {
        return this.mEntityName;
    }
 }
