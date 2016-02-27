package com.sdsmdg.kd.magnetomania;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        double userScore = intent.getDoubleExtra("Your Score:", 0);

        TextView textView = (TextView) findViewById(R.id.user_score);
        textView.setText(Integer.toString((int)userScore));

        GameView.is_game_over    = false;
        GameView.is_game_started = false;
    }
}
