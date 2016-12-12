package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.ScoreBubble;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;

/**
 * @author Haresh Khanna
 */

public class ScoreBubbleController {
    private ScoreBubble scoreBubble;

    public ScoreBubbleController(ScoreBubble scoreBubble) {
        this.scoreBubble = scoreBubble;
    }

    public void control(float delta) {
        if (scoreBubble.active && scoreBubble.activeTime > 0) {
            scoreBubble.move(delta);
            scoreBubble.activeTime--;
        } else {
            scoreBubble.reset();
        }
    }

    public boolean check() {
        if (scoreBubble.dst(InputHandler.touch.x, InputHandler.touch.y) < scoreBubble.radius + 4) {
            GameWorld.gameScore += scoreBubble.scoreValue;
            scoreBubble.reset();
            Gdx.app.log("ScoreAdded", "Score Bubble burst! Score added to main score.");
            return true;
        }
        return false;
    }
}
