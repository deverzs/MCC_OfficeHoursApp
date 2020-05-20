package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;

public class ProfessorLoggedInView extends AppCompatActivity {

    private Spinner chooseSemesterSpinner;
    private Instructor selectedInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_logged_in_view);

        chooseSemesterSpinner = findViewById(R.id.chooseSemesterSpinner);
        final ArrayAdapter<String> instructorSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getSemesterTitles() );
        chooseSemesterSpinner.setAdapter(instructorSpinnerAdapter);

        getIntent();
        selectedInstructor = getIntent().getParcelableExtra("SelectedInstructor");


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
                        intent.putExtra("selectedInstructor", selectedInstructor);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(ProfessorLoggedInView.this, "No office hours uploaded for Summer 2020.", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(ProfessorLoggedInView.this, "No office hours uploaded for Fall 2020.", Toast.LENGTH_LONG).show();
break;
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