package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfessorLoggedInView extends AppCompatActivity {

    private Spinner chooseSemesterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_logged_in_view);

        chooseSemesterSpinner = findViewById(R.id.chooseSemesterSpinner);
        final ArrayAdapter<String> instructorSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getSemesterTitles() );
        chooseSemesterSpinner.setAdapter(instructorSpinnerAdapter);



    }
    public void updateSchedule(View v)
    {
        chooseSemesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(ProfessorLoggedInView.this,  "No semester chosen.", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Intent intent = new Intent(ProfessorLoggedInView.this, updateScheduleActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(ProfessorLoggedInView.this, "No office hours uploaded for Summer 2020.", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(ProfessorLoggedInView.this, "No office hours uploaded for Fall 2020.", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void viewSchedule(View v)
    {
    Intent intent = new Intent(this, ProfessorDetails.class);
    startActivity(intent);

    }

    public String[] getSemesterTitles()
    {
        String[] semesters = new String[4];
        semesters[0] = "[SELECT SEMESTER]";
        semesters[1] = "SPRING 2020";
        semesters[2] = "SUMMER 2020";
        semesters[3] = "FALL 2020";

        return semesters;

    }






}