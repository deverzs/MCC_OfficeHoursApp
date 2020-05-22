package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

/**
 * Activity to Cancel Office Hours. Instructor can access.
 */
public class CancelOfficeHoursActivity extends AppCompatActivity {

    public static final String TAG = CancelOfficeHoursActivity.class.getSimpleName();

    private Button cancelButton;        //button to cancel
    private Instructor instructor;      //instructor who cancels
    private List<Schedule> allSessions;     //all the sessions of all instructors
    private List<Schedule> instructorSessions;  //all the sessions of instructor
    private DBHelper db;
    private Switch[] boxes = new Switch[10];        //Switches


    @Override
    /**
     * Creates the activity and inflates the layout
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_office);

        //Wiring up
        Switch checkBox1 = findViewById(R.id.switch1);
        Switch checkBox2 = findViewById(R.id.switch2);
        Switch checkBox3 = findViewById(R.id.switch3);
        Switch checkBox4 = findViewById(R.id.switch4);
        Switch checkBox5 = findViewById(R.id.switch5);
        Switch checkBox6 = findViewById(R.id.switch6);
        Switch checkBox7 = findViewById(R.id.switch7);
        Switch checkBox8 = findViewById(R.id.switch8);
        Switch checkBox9 = findViewById(R.id.switch9);
        Switch checkBox10 = findViewById(R.id.switch10);
        cancelButton = findViewById(R.id.cancelButton_CANCEL);
        db = new DBHelper(this);
        instructorSessions = new ArrayList<>();

        //Adding switches to the boxes array
        boxes[0] = checkBox1;
        boxes[1] = checkBox2;
        boxes[2] = checkBox3;
        boxes[3] = checkBox4;
        boxes[4] = checkBox5;
        boxes[5] = checkBox6;
        boxes[6] = checkBox7;
        boxes[7] = checkBox8;
        boxes[8] = checkBox9;
        boxes[9] = checkBox10;


        //Get the intent from ProcessorLoggedInView Activity
        Intent intent = getIntent();
        instructor = intent.getParcelableExtra("SelectedInstructor");
        allSessions = db.getAllSchedules();

        //find all the sessions of this instructor
        for(Schedule s: allSessions){
            if(s.getmInstructor().getmId()==instructor.getmId()){
                instructorSessions.add(s);
            }
        }

        //Make all the boxes invisible
        for (int i = 0; i < boxes.length; i++) {
            boxes[i].setVisibility(View.INVISIBLE);
        }

        //Add the schedule sessions to the switches, if they are being used
        String temp;
        for (int i = 0; i < instructorSessions.size() ; i++) {
            //make the switch visible
            boxes[i].setVisibility(View.VISIBLE);
            //check if swtich is currently activated and write the day being accessed
            switch (instructorSessions.get(i).getmOfficeHourDay()) {
                case 0:
                    temp = "On Sabbatical";
                    boxes[i].setText(temp);
                    if(instructorSessions.get(i).getInSession()==0) boxes[i].setChecked(true);
                    break;
                case 1:
                    temp = "MON " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    if(instructorSessions.get(i).getInSession()==0) boxes[i].setChecked(true);
                    break;
                case 2:
                    temp = "TUES " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    if(instructorSessions.get(i).getInSession()==0) boxes[i].setChecked(true);
                    break;
                case 3:
                    temp = "WED " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    if(instructorSessions.get(i).getInSession()==0) boxes[i].setChecked(true);
                    Log.i(TAG, "//checked is" + boxes[i].isChecked());
                    break;
                case 4:
                    temp = "THUR " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    if(instructorSessions.get(i).getInSession()==0) boxes[i].setChecked(true);
                    break;
                case 5:
                    temp = "FRI " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    if(instructorSessions.get(i).getInSession()==0) boxes[i].setChecked(true);
                    break;
            }
        }

    }

    /**
     * Cancel the sessions button
     * @param v Cancel button
     */
    public void handleDeleteSessions(View v){
        //check which switch is cancelled
        for (int i = 0; i < instructorSessions.size() ; i++) {
            //if the session is checked
            if(boxes[i].isChecked()){
                //get the session and update it
                instructorSessions.get(i).setInSession(0);
                db.updateSchedule(instructorSessions.get(i));
            }
            else {
               // if the session is not checked
                instructorSessions.get(i).setInSession(1);
                db.updateSchedule(instructorSessions.get(i));
            }
        }

        //Move the user back to the ProfessorLoggedInView
        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        intent.putExtra("FromActivity", "professor");
        intent.putExtra("SelectedInstructor", instructor);
        startActivity(intent);
    }

    /**
     * Return the Instructor to the Office Hours Page, ProfessorLoggedInView
     * @param v Return to Office Hours Button
     */
    public void handleReturnToOH(View v){
        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        intent.putExtra("SelectedInstructor", instructor);
        intent.putExtra("FromActivity", "professor");
        startActivity(intent);
    }
}
