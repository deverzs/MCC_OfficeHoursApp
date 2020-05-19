package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.SavedInstructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;
import edu.miracostacollege.mcc_officehoursapp.Model.professorsListViewAdapter;

import static edu.miracostacollege.mcc_officehoursapp.Model.DBHelper.DATABASE_NAME;

//for student view
public class LoggedinSavedProfs extends AppCompatActivity {

    private static final String TAG = LoggedinSavedProfs.class.getSimpleName();
    private DBHelper db;

    private List<SavedInstructor> instructorList;
    private SavedInstructorListAdapter instructorListAdapter;
    private ListView instructorListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin_saved_profs);
        db = new DBHelper(this);

        //db.deleteAllSavedInstructors();
        //db.addSavedInstructor(2); //dummy
        //db.addSavedInstructor(3); //dummy

        instructorList = db.getAllSavedInstructors();
        int count = 0;
        for(SavedInstructor s: instructorList){
            Log.i(TAG, "//// instructor count : " + count++ + " " + s.getmInstructor().getmFullName());
        }

        //Instructor List Adapter
        instructorListAdapter = new SavedInstructorListAdapter(this,
                R.layout.activity_saved_professor_list_item, instructorList);

        instructorListView = findViewById(R.id.professorsListView_SAVED);
        instructorListView.setAdapter(instructorListAdapter);

        for(SavedInstructor i : instructorList){
            Log.i(TAG, "//SAVED: " + i.getmInstructor().getmFullName());
        }



    }

    public void viewInstructorDetails(View v){
        SavedInstructor selectedInstuctor = (SavedInstructor) v.getTag();

        Intent intent = new Intent(this, ProfessorDetails.class);
        intent.putExtra("Instructor", selectedInstuctor.getmInstructor().getmId());
        startActivity(intent);

    }

    public void handleNewSearch(View v)
    {
        Intent intent = new Intent(this, StudentSearch.class);
        startActivity(intent);
    }
}
