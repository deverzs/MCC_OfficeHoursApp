package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

public class CancelOfficeHoursActivity extends AppCompatActivity {

    public static final String TAG = CancelOfficeHoursActivity.class.getSimpleName();
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private Button cancelButton;
    private Instructor instructor;
    private List<Schedule> allSessions;
    private List<Schedule> instructorSessions;
    private DBHelper db;
    private CheckBox[] boxes = new CheckBox[9];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_office_hours);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        boxes[0] = checkBox1;
        boxes[1] = checkBox2;
        boxes[2] = checkBox3;
        boxes[3] = checkBox4;
        boxes[4] = checkBox5;
        boxes[5] = checkBox6;
        boxes[6] = checkBox7;
        boxes[7] = checkBox8;
        boxes[8] = checkBox9;
        cancelButton = findViewById(R.id.cancellButton_CANCEL);
        db = new DBHelper(this);
        instructorSessions = new ArrayList<>();

        Intent intent = getIntent();
        instructor = intent.getParcelableExtra("Instructor");
        allSessions = db.getAllSchedules();

        for(Schedule s: allSessions){
            if(s.getmInstructor().getmId()==instructor.getmId()){
                instructorSessions.add(s);
            }
        }

        for (int i = 0; i < 9; i++) {
            boxes[i].setVisibility(View.INVISIBLE);
        }
        String temp;
        for (int i = 0; i < instructorSessions.size() ; i++) {
            boxes[i].setVisibility(View.VISIBLE);

            switch (instructorSessions.get(i).getmOfficeHourDay()) {
                case 0:
                    temp = "On Sabbatical";
                    boxes[i].setText(temp);
                    break;
                case 1:
                    temp = "MON " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    break;
                case 2:
                    temp = "TUES " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    break;
                case 3:
                    temp = "WED " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    break;
                case 4:
                    temp = "THUR " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    break;
                case 5:
                    temp = "FRI " + instructorSessions.get(i).getmOfficeHourTime();
                    boxes[i].setText(temp);
                    break;
            }
        }



    }

    public void handleDeleteSessions(View v){
        for (int i = 0; i < instructorSessions.size() ; i++) {
            if(boxes[i].isChecked()) db.deleteSchedule(instructorSessions.get(i));
        }

        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        startActivity(intent);
    }

    public void handleReturnToOH(View v){
        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        startActivity(intent);
    }
}
