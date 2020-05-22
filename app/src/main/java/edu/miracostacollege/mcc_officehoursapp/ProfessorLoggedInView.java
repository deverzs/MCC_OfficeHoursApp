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

/**
 * Professors Landing activity
 */
public class ProfessorLoggedInView extends AppCompatActivity {

    public static final String TAG = ProfessorLoggedInView.class.getSimpleName();
    private Spinner chooseSemesterSpinner;      //spinner for semester
    private Instructor selectedInstructor;      //Instructor accessing activity


    @Override
    /**
     * Create and inflate the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_logged_in_view);

        //wire up
        chooseSemesterSpinner = findViewById(R.id.chooseSemesterSpinner);
        final ArrayAdapter<String> instructorSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getSemesterTitles() );
        chooseSemesterSpinner.setAdapter(instructorSpinnerAdapter);

        //get the intent and the instructor passed through
        getIntent();
        selectedInstructor = getIntent().getParcelableExtra("SelectedInstructor");

        //Check the spinner
        chooseSemesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * Check the spinner selection
             */
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

    /**
     * Move the Instructor to the CnacelOfficeHoursActivity
     * @param v  Update Schedule button
     */
    public void updateSchedule(View v)
    {
        //based on the spinner choice, move the user to the CancelOfficeHours
        if(chooseSemesterSpinner.getSelectedItemPosition() ==0){
            Toast.makeText(ProfessorLoggedInView.this,  "No semester chosen.",
                    Toast.LENGTH_LONG).show();
        }
        else if( chooseSemesterSpinner.getSelectedItemPosition()== 2 ||
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

    /**
     * Move the Instructor to the Details Instructor activity to view their own schedule
     * @param v  View schedule button
     */
    public void viewSchedule(View v)
    {
        //based on the spinner choice, move the user to the activity
        if(chooseSemesterSpinner.getSelectedItemPosition() ==0){
            Toast.makeText(ProfessorLoggedInView.this,  "No semester chosen.",
                    Toast.LENGTH_LONG).show();
        }
        else if( chooseSemesterSpinner.getSelectedItemPosition()== 2 ||
                chooseSemesterSpinner.getSelectedItemPosition() == 3)  {
            Toast.makeText(this, "No schedule uploaded.", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, ProfessorDetails.class);
            intent.putExtra("SelectedInstructor", selectedInstructor);
            intent.putExtra("FromActivity", "professor");
            startActivity(intent);
        }

    }

    /**
     * Get the name of the semester to populate the spinner options
     * @return  String array of semester titles
     */
    public String[] getSemesterTitles()
    {
        String[] semesters = new String[4];
        semesters[0] = "[SELECT SEMESTER]";
        semesters[1] = "SPRING 2020";
        semesters[2] = "SUMMER 2020";
        semesters[3] = "FALL 2020";

        return semesters;
    }

    /**
     * Logout activity
     * @param v  Log out activity button
     */
    public void handleLogOut(View v){
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }


}