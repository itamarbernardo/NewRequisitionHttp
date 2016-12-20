package com.itamarbernardo.requisitionhttp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
 * Created by ANAFLAVIA on 20/12/2016.
 */
public class SplashScreen extends ActionBarActivity implements Runnable{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
