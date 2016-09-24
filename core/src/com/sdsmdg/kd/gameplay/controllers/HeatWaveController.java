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
    private HeatWave heatWave;

    public HeatWaveController(HeatWave heatWave) {
        this.heatWave = heatWave;
    }

    public void control(float delta) {
        if (heatWave.active) {
            if (heatWave.radius <= Main.d) {
                heatWave.expand(delta);
            } else {
                heatWave.reset();
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }
        }
    }

    public boolean check (Magnus magnus) {
        float distance = heatWave.dst(InputHandler.touch.x, InputHandler.touch.y);
        int angle = (int)(MathUtils.atan2(InputHandler.touch.y - heatWave.y,
                InputHandler.touch.x - heatWave.x) * (180 / MathUtils.PI));

        if (distance <= heatWave.radius + 5 && distance >= heatWave.radius - 30) {
            for(int i = 0; i < 9; i++) {
                if (angle > 40 * i && angle < 40 * i + 25)
                    return true;
            }
        }
        return false;
    }
}
