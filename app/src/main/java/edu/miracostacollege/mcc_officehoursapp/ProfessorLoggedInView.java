package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProfessorLoggedInView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_logged_in_view);
    }
    public void updateSchedule(View v)
    {
        Intent intent = new Intent(this, updateScheduleActivity.class);
        startActivity(intent);
    }
}