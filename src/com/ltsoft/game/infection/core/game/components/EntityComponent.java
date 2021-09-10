package com.ltsoft.game.infection.core.game.components;

import com.ltsoft.game.infection.core.game.Entity;

public class EntityComponent {

    // Inseriamo il riferimento alla Entity:
    public Entity gameObject;

    /** Metodo facoltativo della inizializzazione. */
    public void onInit() {
        // Nothing to do now.
    }

    /** Metodo da overridare per eseguire il codice del component. */
    public void onUpdate(float fDeltaTime) {
        // Nothing to do now.
    }
}
