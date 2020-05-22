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
import edu.miracostacollege.mcc_officehoursapp.professorsListViewAdapter;

import static edu.miracostacollege.mcc_officehoursapp.Model.DBHelper.DATABASE_NAME;

/**
 * The Student View of their Saved Professors
 */
public class LoggedinSavedProfs extends AppCompatActivity {

    private static final String TAG = LoggedinSavedProfs.class.getSimpleName();
    private DBHelper db;


    @Override
    /**
     * Create and inflate the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin_saved_profs);
        db = new DBHelper(this);

        //all the saved instructors of the user
        List<SavedInstructor> instructorList = db.getAllSavedInstructors();

        //Instructor List Adapter
        SavedInstructorListAdapter instructorListAdapter = new SavedInstructorListAdapter(this,
                R.layout.activity_saved_professor_list_item, instructorList);

        //wire up the list view and set to List adapter
        ListView instructorListView = findViewById(R.id.professorsListView_SAVED);
        instructorListView.setAdapter(instructorListAdapter);

    }

    /**
     * Move the user to the Details of the Professor
     * @param v the list item
     */
    public void viewInstructorDetails(View v){
        //get the list item tag
        SavedInstructor selectedInstuctor = (SavedInstructor) v.getTag();

        Intent intent = new Intent(this, ProfessorDetails.class);
        intent.putExtra("SelectedInstructor", selectedInstuctor.getmInstructor());
        intent.putExtra("FromActivity", "savedSearch");
        startActivity(intent);

    }

    /**
     * User moves to Search activity
     * @param v button for new search
     */
    public void handleNewSearch(View v)
    {
        Intent intent = new Intent(this, StudentSearch.class);
        intent.putExtra("FromActivity", "savedSearch");
        startActivity(intent);
    }

    /**
     * Move user to the login page
     * @param v Button login page
     */
    public void handleLogOut(View v){
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }

}
