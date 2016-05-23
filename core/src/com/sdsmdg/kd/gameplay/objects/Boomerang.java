package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.MathUtils;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */
public class Boomerang extends GameObject{

    public Boomerang () {
        // Setting the radius such that the boomerang is (1/100)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea/(100* MathUtils.PI));

        // The boomerang should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 30;

        // Boomerang is inactive when game starts.
        this.active = false;
    }
}
