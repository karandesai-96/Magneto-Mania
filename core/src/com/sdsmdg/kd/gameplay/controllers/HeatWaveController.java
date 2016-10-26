package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.sdsmdg.kd.gameplay.objects.HeatWave;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Karan Desai
 */
public class HeatWaveController {
    private HeatWave[] heatWaves;

    public HeatWaveController(HeatWave[] heatWaves) {
        this.heatWaves = heatWaves;
    }

    public void control(float delta) {
        for (HeatWave heatWave : heatWaves) {
            if (heatWave.active) {
                if (heatWave.radius <= Main.d) {
                    heatWave.expand(delta);
                } else {
                    heatWave.reset();
                }
            }
        }
        if (!heatWaves[0].active) {
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }

    public boolean check(Magnus magnus) {
        float distance, angle;

        for (int i = 0; i < heatWaves.length; i++) {
            distance = heatWaves[i].dst(InputHandler.touch.x, InputHandler.touch.y);
            angle = (int) (MathUtils.atan2(InputHandler.touch.y - heatWaves[i].y,
                    InputHandler.touch.x - heatWaves[i].x) * (180 / MathUtils.PI));

            if (distance <= heatWaves[i].radius + 5 && distance >= heatWaves[i].radius - 30) {
                if (i % 2 == 1) {
                    return ((angle > 30 && angle < 60)  || (angle > 90  && angle < 120) || (angle > 150  && angle < 180) ||
                            (angle < 0  && angle > -30) || (angle < -60 && angle > -90) || (angle < -120 && angle > -150));
                }
                else {
                    return (angle > 0   && angle < 30)  || (angle > 60  && angle < 90)   || (angle > 120 && angle < 150) ||
                            (angle < -30 && angle > -60) || (angle < -90 && angle > -120) || (angle < -150 && angle > -180);
                }
            }
        }
        return false;
    }
}
