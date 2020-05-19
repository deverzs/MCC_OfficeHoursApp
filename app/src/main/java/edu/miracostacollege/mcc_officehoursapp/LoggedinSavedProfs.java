package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

       // deleteDatabase(DATABASE_NAME);
        db.addSavedInstructor(2); //dummy
        db.addSavedInstructor(3); //dummy

        instructorList = db.getAllSavedInstructors();

        //Instructor List Adapter
        instructorListAdapter = new SavedInstructorListAdapter(this,
                R.layout.activity_professor_list_item, instructorList);

        instructorListView = findViewById(R.id.professorsListView_SAVED);
        instructorListView.setAdapter(instructorListAdapter);



    }

    public void viewInstructorDetails(View v){
        Instructor selectedInstuctor = (Instructor) v.getTag();

        Intent intent = new Intent(this, ProfessorDetails.class);
        intent.putExtra("Instructor", selectedInstuctor.getmId());
        startActivity(intent);

    }

    public void handleNewSearch(View v)
    {
        Intent intent = new Intent(this, StudentSearch.class);
        startActivity(intent);
    }
}
