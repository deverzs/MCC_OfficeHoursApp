package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Verification;

public class ProfessorLoggedInView extends AppCompatActivity {

    public static final String TAG = ProfessorLoggedInView.class.getSimpleName();
    private Spinner chooseSemesterSpinner;
    private Instructor selectedInstructor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_logged_in_view);

        chooseSemesterSpinner = findViewById(R.id.chooseSemesterSpinner);
        final ArrayAdapter<String> instructorSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getSemesterTitles() );
        chooseSemesterSpinner.setAdapter(instructorSpinnerAdapter);

        getIntent();
        selectedInstructor = getIntent().getParcelableExtra("SelectedInstructor");
        Log.i(TAG, "//LOGGEDIN: " + selectedInstructor.getmFullName());


        //I moved here
        chooseSemesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                         break;
                    case 2:
                        Toast.makeText(ProfessorLoggedInView.this,
                                "No office hours uploaded for Summer 2020.", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(ProfessorLoggedInView.this,
                                "No office hours uploaded for Fall 2020.", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void updateSchedule(View v)
    {
        if(chooseSemesterSpinner.getSelectedItemPosition() ==0){
            Toast.makeText(ProfessorLoggedInView.this,  "No semester chosen.",
                    Toast.LENGTH_LONG).show();
        }
        else if(     chooseSemesterSpinner.getSelectedItemPosition()== 2 ||
                chooseSemesterSpinner.getSelectedItemPosition() == 3)  {
            Toast.makeText(this, "No schedule uploaded.", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, CancelOfficeHoursActivity.class);
            Log.i(TAG, "//Instructor pass:" + selectedInstructor.getmFullName());
            intent.putExtra("SelectedInstructor", selectedInstructor);
            intent.putExtra("FromActivity", "professor");
            startActivity(intent);
        }


    }

    public void viewSchedule(View v)
    {
        if(chooseSemesterSpinner.getSelectedItemPosition() ==0){
            Toast.makeText(ProfessorLoggedInView.this,  "No semester chosen.",
                    Toast.LENGTH_LONG).show();
        }
        else if(     chooseSemesterSpinner.getSelectedItemPosition()== 2 ||
                chooseSemesterSpinner.getSelectedItemPosition() == 3)  {
            Toast.makeText(this, "No schedule uploaded.", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, ProfessorDetails.class);
            Log.i(TAG, "//Instructor pass:" + selectedInstructor.getmFullName());
            intent.putExtra("SelectedInstructor", selectedInstructor);
            intent.putExtra("FromActivity", "professor");
            startActivity(intent);
        }

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

    public void handleLogOut(View v){
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }




}