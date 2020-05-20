package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.SavedInstructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

public class ProfessorDetails extends AppCompatActivity {

    public static final String TAG = ProfessorDetails.class.getSimpleName();
    private List<Schedule> alSchedulesList ;
    private List<Schedule> selectedScheduleList ;
    private List<SavedInstructor> savedInstructorList ;
    private ScheduleListAdapter scheduleListAdapter;
    private ListView scheduleListView;
    private DBHelper db;
    private Instructor instructor;
    private long instructorID;

    TextView professorNameTextView;
    TextView professorPhoneTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_details);
        professorNameTextView  = findViewById(R.id.professorNameTextView_DETAILS);
        professorPhoneTextView = findViewById(R.id.extensionTextView_DETAILS);
        db = new DBHelper(this);

        alSchedulesList = db.getAllSchedules();
        savedInstructorList = db.getAllSavedInstructors();
        selectedScheduleList = new ArrayList<>();

        Intent intent = getIntent();
        Instructor instructor = intent.getParcelableExtra("SelectedInstructor");


        professorNameTextView.setText(instructor.getmFullName());
        professorPhoneTextView.setText(instructor.getmPhone());
        int count = 0;
        selectedScheduleList.clear();
        Log.i(TAG, "//Size selected sch:" + selectedScheduleList.size());
        for(Schedule s: alSchedulesList){
            for(SavedInstructor i : savedInstructorList){
                if(s.getmInstructor().getmId()==i.getmInstructor().getmId()){
                    if(s.getmInstructor().getmId()==instructorID)
                    selectedScheduleList.add(s);
                }
            }

        }


        //Schedule List Adapter
        scheduleListAdapter = new ScheduleListAdapter(this,
                R.layout.activity_schedule_list_item, selectedScheduleList);
        scheduleListView = findViewById(R.id.sessionListView_DETAILS);
        scheduleListView.setAdapter(scheduleListAdapter);


    }

    public void handleSaveProfessor(View v){
        boolean added = false;
        for(SavedInstructor s: savedInstructorList){
            if(s.getmInstructor().getmId() == instructorID ) added=true;
        }
        if(added){
            Toast.makeText(this, "That Professor is already saved.", Toast.LENGTH_LONG).show();
        }
        else {
            db.addSavedInstructor(instructorID);
            Toast.makeText(this, "Professor has been saved.", Toast.LENGTH_LONG).show();
        }

    }

    public void handleDeleteProfessor(View v){
        for(SavedInstructor s: savedInstructorList){
            if(s.getmInstructor().getmId() == instructorID) db.deleteSavedInstructor(s);
            Toast.makeText(this, "Professor has been deleted from saved.", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, StudentSearch.class);
        startActivity(intent);
    }

}
