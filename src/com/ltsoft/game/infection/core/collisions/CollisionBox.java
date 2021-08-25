package com.ltsoft.game.infection.core.collisions;

import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.Size;

import java.awt.*;

public class CollisionBox {

    private Rectangle bounds;

    public CollisionBox(CollisionBox collisionBox) {
        this.bounds = new Rectangle();
        this.bounds.x = collisionBox.getBounds().x;
        this.bounds.y = collisionBox.getBounds().y;
        this.bounds.width = collisionBox.getBounds().width;
        this.bounds.height = collisionBox.getBounds().height;
    }

    public CollisionBox() {
        this(new Rectangle());
    }

    public CollisionBox(Rectangle bounds) {
        this.bounds = bounds;
    }

    public CollisionBox copy() {
        return new CollisionBox(this);
    }

    public void setSize(Size size) {
        this.bounds.setSize( size.getWidth(), size.getHeight() );
    }

    public void setLocation(Position position) {
        this.bounds.setLocation( position.getX(), position.getY() );
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public boolean collides(CollisionBox otherBox) {
        return this.bounds.intersects( otherBox.getBounds() );
    }
}
