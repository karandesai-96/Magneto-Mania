package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;


public class GameWorld {

    /** CLASS MEMBERS *******************************************************/
    private Rectangle rect = new Rectangle(0, 0, 17, 12);
//  private Magnus magnus;
//    private MagnusCOntroller magnusController;

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        rect.x++;
        if (rect.x > 137) {
            rect.x = 0;
        }
    }

    public Rectangle getRect() {
        return rect;
    }
}
