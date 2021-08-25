package com.ltsoft.game.infection.core.game.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entity {

    // Nome della entity:
    private String mEntityName;

    // Lista dei componenti della entity:
    private List<Component> mComponentList;


    /** Creazione di una entity vuota. */
    public Entity(String mEntityName) {
        this.mEntityName = mEntityName;
        this.mComponentList = new ArrayList<>();
    }


    /** Aggiungo un componente alla entity. */
    public void addComponent(Component c) {
        this.mComponentList.add( c );
    }


    /** Prelevo un componente castandolo. */
    public <T extends Component> T getComponent(Class<T> componentClass) {
        Iterator<Component> componentIterator = this.mComponentList.iterator();
        while(componentIterator.hasNext()) {
            Component component = componentIterator.next();
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
