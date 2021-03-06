package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash activity
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    /**
     * Create and inflate the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //wire it up
        ImageView star1_SPLASH = findViewById(R.id.star1_SPLASH);
        ImageView star2_SPLASH = findViewById(R.id.star2_SPLASH);
        ImageView star3_SPLASH = findViewById(R.id.star3_SPLASH);

        //Animation here
        Animation shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
        for(int i = 0; i < 5; i++) {
            star1_SPLASH.startAnimation(shakeAnim);
            star2_SPLASH.startAnimation(shakeAnim);
            star3_SPLASH.startAnimation(shakeAnim);
        }

        //wait 2-3 seconds, then fire Intent to main ativity
        //TimerTask waits a period of time before performing a task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Create an Intent to go to MainActivity
                Intent intent = new Intent(SplashActivity.this, LoginUser.class);
                startActivity(intent);
                //finish splashActivity
                finish();
            }
        };

        //let's define a timer for when task should happen
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }
}


