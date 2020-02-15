package com.demo.splashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

    MediaPlayer introSound;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This code will render a full screen view
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        // Include background audio/sound
        introSound = MediaPlayer.create(SplashActivity.this, R.raw.intro);
        introSound.start();
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 5000) {
                        sleep(100);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    Intent i = new Intent();
                    i.setClassName("com.demo.splashapp",
                            "com.demo.splashapp.MainActivity");
                    startActivity(i);
                }
            }
        };
        splashThread.start();
    }

    @Override
    public void onPause(){
        super.onPause();
        introSound.release();
        finish();
    }

}
