package com.sdsmdg.kd.magnetomania;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlay(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}
