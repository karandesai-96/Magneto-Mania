package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */

public class ScoreBubble extends GameObject {
    private RandomXS128 random;
    private int addCount;
    public int activeTime;
    public int scoreValue;

    public ScoreBubble() {
        this.random = new RandomXS128();

        // Setting the radius such that the score bubble is (1/100)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea / (100 * MathUtils.PI));

        // The score bubble should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        // Velocity of Score Bubble can be anything between 5 and 20 units.
        this.velocity = random.nextInt(16) + 5;
        this.velocity *= Main.scaleFactor;

        // The variable 'scoreValue' is the value of the score that will be added upon
        // touching the bubble.
        this.scoreValue = 0;

        // The variable 'activeTime' is time till which the score bubble follows finger.
        // Initially set to '0'.
        this.activeTime = 0;

        // The variable 'addCount' stores the number of times scoreBubble is updated.
        this.addCount = 0;

        // Score Bubble is inactive when game starts
        this.active = false;
    }

    public void init(int scoreValue) {
        activate();
        activeTime = random.nextInt(200) + 100;

        this.x = ((int) (Main.screen.x)) * random.nextFloat();
        this.y = ((int) (Main.screen.y)) * random.nextFloat();

        this.scoreValue = scoreValue;

        this.addCount = 0;

        calcVelocityComponent(new Vector2(random.nextInt((int) Main.screen.x), random.nextInt((int) Main.screen.y)));
    }

    public void move(float delta) {
        mulAdd(velocityComponent, delta);
        addCount = (addCount + 1) % 1000;
        if (addCount == 999 && random.nextBoolean()) {
            calcVelocityComponent(new Vector2(random.nextInt((int) Main.screen.x), random.nextInt((int) Main.screen.y)));
        }
    }

    public void reset() {
        deactivate();

        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);
    }
}
