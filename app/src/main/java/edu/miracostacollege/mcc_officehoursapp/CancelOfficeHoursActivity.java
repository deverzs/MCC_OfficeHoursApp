package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

public class CancelOfficeHoursActivity extends AppCompatActivity {

    public static final String TAG = CancelOfficeHoursActivity.class.getSimpleName();
    private Switch checkBox1;
    private Switch checkBox2;
    private Switch checkBox3;
    private Switch checkBox4;
    private Switch checkBox5;
    private Switch checkBox6;
    private Switch checkBox7;
    private Switch checkBox8;
    private Switch checkBox9;
    private Switch checkBox10;
    private Button cancelButton;
    private Instructor instructor;
    private List<Schedule> allSessions;
    private List<Schedule> instructorSessions;
    private DBHelper db;
    private Switch[] boxes = new Switch[10];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_office);

        checkBox1 = findViewById(R.id.switch1);
        checkBox2 = findViewById(R.id.switch2);
        checkBox3 = findViewById(R.id.switch3);
        checkBox4 = findViewById(R.id.switch4);
        checkBox5 = findViewById(R.id.switch5);
        checkBox6 = findViewById(R.id.switch6);
        checkBox7 = findViewById(R.id.switch7);
        checkBox8 = findViewById(R.id.switch8);
        checkBox9 = findViewById(R.id.switch9);
        checkBox10 = findViewById(R.id.switch10);
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
        cancelButton = findViewById(R.id.cancelButton_CANCEL);
        db = new DBHelper(this);
        instructorSessions = new ArrayList<>();

        Intent intent = getIntent();
        instructor = intent.getParcelableExtra("SelectedInstructor");
        allSessions = db.getAllSchedules();

        for(Schedule s: allSessions){
            if(s.getmInstructor().getmId()==instructor.getmId()){
                instructorSessions.add(s);
            }
        }

        for (int i = 0; i < boxes.length; i++) {
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
            if(boxes[i].isChecked()){
               // db.deleteSchedule(instructorSessions.get(i));
                instructorSessions.get(i).setInSession(0);

                db.updateSchedule(instructorSessions.get(i));

                /*
                Schedule schedule = instructorSessions.get(i);
                long id = schedule.getmId();
                Instructor instructor = schedule.getmInstructor();
                int section =schedule.getmOfficeHourSection();
                int day = schedule.getmOfficeHourDay();
                String time = schedule.getmOfficeHourTime();
                String loc = schedule.getmOfficeHourLocation();
                int session = 0;
                schedule = new Schedule(id, instructor, section, day, time, loc, session);
                db.updateSchedule(schedule);

                 */
            }
            else {
               // db.deleteSchedule(instructorSessions.get(i));
                instructorSessions.get(i).setInSession(1);

                db.updateSchedule(instructorSessions.get(i));
                /*
                Schedule schedule = instructorSessions.get(i);
                long id = schedule.getmId();
                Instructor instructor = schedule.getmInstructor();
                int section =schedule.getmOfficeHourSection();
                int day = schedule.getmOfficeHourDay();
                String time = schedule.getmOfficeHourTime();
                String loc = schedule.getmOfficeHourLocation();
                int session = 1;
                schedule = new Schedule(id, instructor, section, day, time, loc, session);
                db.updateSchedule(schedule);

                 */
            }
        }

        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        intent.putExtra("FromActivity", "professor");
        intent.putExtra("SelectedInstructor", instructor);
        startActivity(intent);
    }

    public void handleReturnToOH(View v){
        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        intent.putExtra("SelectedInstructor", instructor);
        intent.putExtra("FromActivity", "professor");
        startActivity(intent);
    }
}
