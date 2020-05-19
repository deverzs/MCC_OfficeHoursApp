package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

public class ProfessorDetails extends AppCompatActivity {

    private List<Schedule> scheduleList ;
    private List<Schedule> scheduleListSelected ;
    private ScheduleListAdapter scheduleListAdapter;
    private ListView scheduleListView;
    private DBHelper db;

    TextView professorNameTextView;
    TextView professorPhoneTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_details);
        professorNameTextView  = findViewById(R.id.professorNameTextView_DETAILS);
        professorPhoneTextView = findViewById(R.id.extensionTextView_DETAILS);
        db = new DBHelper(this);
        scheduleList = db.getAllSchedules();

        Intent intent = getIntent();
        Long instructorID = intent.getLongExtra("Instructor", 0);
        Instructor instructor = db.getInstructor(instructorID);

        professorNameTextView.setText(instructor.getmFullName());
        professorPhoneTextView.setText(instructor.getmPhone());
/*
        for(Schedule s: scheduleList){
            if(s.getmInstructor().getmId()==instructorID){
                scheduleListSelected.add(s);
            }
        }
        //Schedule List Adapter
        scheduleListAdapter = new ScheduleListAdapter(this,
                R.layout.activity_schedule_list_item, scheduleListSelected);
        scheduleListView = findViewById(R.id.sessionListView_DETAILS);
        scheduleListView.setAdapter(scheduleListAdapter);
        */

    }


}
