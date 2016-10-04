package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.math.Vector2;


public abstract class GameObject extends Vector2 {
    public float radius;
    public float velocity;
    public boolean active;
    public Vector2 velocityComponent = new Vector2(0, 0);

    public GameObject() {
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void calcVelocityComponent(Vector2 destination) {
        float distance = dst(destination);

        // velocity times cos(theta)
        velocityComponent.x = velocity * (destination.x - this.x) / distance;

        // velocity times sin(theta)
        velocityComponent.y = velocity * (destination.y - this.y) / distance;
    }

    public void calcVelocityComponent(Vector2 source, Vector2 destination) {
        float distance = source.dst(destination);

        // velocity times cos(theta)
        velocityComponent.x = velocity * (destination.x - source.x) / distance;

        // velocity times sin(theta)
        velocityComponent.y = velocity * (destination.y - source.y) / distance;
    }
}
