package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.MathUtils;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */

public class ScoreBubble extends GameObject {
    public ScoreBubble() {
        // Setting the radius such that the score bubble is (1/100)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea / (100 * MathUtils.PI));

        // The score bubble should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);
    }
}
