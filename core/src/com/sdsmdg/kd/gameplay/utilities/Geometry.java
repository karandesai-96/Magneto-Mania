package com.sdsmdg.kd.gameplay.utilities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Haresh on 09-03-2016.
 */
public class Geometry {
    public static Vector2 calcVelocityComponents(Vector2 a, Vector2 b, int velocity) {
        int distance = Geometry.distance(a, b);

        Vector2 mVelocityComponent = new Vector2(0,0);
        mVelocityComponent.x = velocity * (a.x - b.x) / distance;  // velocity times cos(theta)
        mVelocityComponent.y = velocity * (a.y - b.y) / distance;  // velocity times sin(theta)

        return mVelocityComponent;
    }

    public static int distance(Vector2 a, Vector2 b) {
        return (int)Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }
}
