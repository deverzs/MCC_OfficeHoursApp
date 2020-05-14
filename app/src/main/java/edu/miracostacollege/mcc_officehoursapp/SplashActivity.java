package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //wait 2-3 seconds, then fire Intent to main ativity
        //TimerTask waits a period of time before performing a task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Create an Intent to go to MainActivity
                Intent intent = new Intent(SplashActivity.this, Login.class);
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

