package com.sdsmdg.kd.magnetomania;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

    private Display mGameDisplay;
    public static Point mScreenSize = new Point(0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mGameDisplay = getWindowManager().getDefaultDisplay();
        mGameDisplay.getSize(mScreenSize);
        // My testing device has 480px width, 782px height.

        setContentView(new GameView(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameView.is_game_over = true;
        GameView.mThread.setRunning(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.tryGameOver();
    }
}
