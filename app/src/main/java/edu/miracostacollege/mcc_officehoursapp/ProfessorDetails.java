package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private List<SavedInstructor> savedInstructorList ;
    private DBHelper db;
    private Instructor instructor;  //Instructor being references
    private long instructorID;      //id of the instructor
    private String fromActivity;    //represents the activity being referenced

    @Override
    /**
     * Create and inflate the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_details);

        //wiring up views
        TextView professorNameTextView = findViewById(R.id.professorNameTextView_DETAILS);
        TextView professorPhoneTextView = findViewById(R.id.extensionTextView_DETAILS);
        Button saveProfessorBuutton = findViewById(R.id.saveProfessorButton_DETAILS);
        Button deleteProfessorButton = findViewById(R.id.deleteProfessorButton_DETAILS);
        Button backToSearchButton = findViewById(R.id.backToSearchButton_DETAILS);
        Button backToSavedButton = findViewById(R.id.backToSavedButton_DETAILS);
        TextView availableByApptTextView = findViewById(R.id.availableByApptTextView_DETAILS);
        Button backtoOfficeHours = findViewById(R.id.returnToOHButton_DETAILS);

        db = new DBHelper(this);

        //creating lists from the database
        List<Schedule> alSchedulesList = db.getAllSchedules();
        savedInstructorList = db.getAllSavedInstructors();
        List<Schedule> selectedScheduleList = new ArrayList<>();

        //get the intent from the activity coming from
        Intent intent = getIntent();
        instructor = intent.getParcelableExtra("SelectedInstructor");
        fromActivity = intent.getStringExtra("FromActivity");

        //get important details of the instructor and set the text views
        instructorID = instructor.getmId();
        professorNameTextView.setText(instructor.getmFullName());
        professorPhoneTextView.setText(instructor.getmPhone());

        //check and set the appointment option of instructor
        String temp = "Available by Appointment";
        if(instructor.byAppointment()==1) availableByApptTextView.setText(temp);
        else availableByApptTextView.setText("");

        //clear the selected chedule list
        selectedScheduleList.clear();

        //depending on where the intent is originating, assign the buttons and read
        //the schedules and assign the selected schedule list
        if(fromActivity !=null && fromActivity.equals("saved")) {
            for (Schedule s : alSchedulesList) {
                for (SavedInstructor i : savedInstructorList) {
                    if (s.getmInstructor().getmId() == i.getmInstructor().getmId()) {
                        if (s.getmInstructor().getmId() == instructorID)
                            selectedScheduleList.add(s);
                    }
                }
            }
        }else if ((fromActivity !=null) && (fromActivity.equals("search"))){
            saveProfessorBuutton.setVisibility(View.INVISIBLE);
            deleteProfessorButton.setVisibility(View.INVISIBLE);
            backToSavedButton.setVisibility(View.INVISIBLE);
            for (Schedule s : alSchedulesList) {
                    if (s.getmInstructor().getmId() == instructorID) {
                            selectedScheduleList.add(s);
                    }
            }
        }
        else if ((fromActivity !=null) && (fromActivity.equals("savedSearch"))){
                //backToSearchButton.setVisibility(View.INVISIBLE);
                for (Schedule s : alSchedulesList) {
                    if (s.getmInstructor().getmId() == instructorID) {
                        selectedScheduleList.add(s);
                    }
                }
        }
        else if ((fromActivity !=null) && (fromActivity.equals("professor"))) {
              backtoOfficeHours.setVisibility(View.VISIBLE);
              backToSearchButton.setVisibility(View.INVISIBLE);
              saveProfessorBuutton.setVisibility(View.INVISIBLE);
              deleteProfessorButton.setVisibility(View.INVISIBLE);
            backToSavedButton.setVisibility(View.INVISIBLE);
            for (Schedule s : alSchedulesList) {
                if (s.getmInstructor().getmId() == instructorID) {
                    selectedScheduleList.add(s);
                }
            }
        }

        //Schedule List Adapter
        ScheduleListAdapter scheduleListAdapter = new ScheduleListAdapter(this,
                R.layout.activity_schedule_list_item, selectedScheduleList);
        ListView scheduleListView = findViewById(R.id.sessionListView_DETAILS);
        scheduleListView.setAdapter(scheduleListAdapter);
    }

    /**
     * Save the Professor to the user's database
     * @param v Save professor button
     */
    public void handleSaveProfessor(View v){
        boolean added = false;  //check if already added to the saved list
        for(SavedInstructor s: savedInstructorList){
            if(s.getmInstructor().getmId() == instructorID ) added=true;
        }
        if(added){
            Toast.makeText(this, "That Professor is already saved.", Toast.LENGTH_SHORT).show();
        }
        else  {
            //adding the selected professor to the saved database
            db.addSavedInstructor(instructorID);
            savedInstructorList = db.getAllSavedInstructors();
            savedInstructorList = db.getAllSavedInstructors();
            Toast.makeText(this, "Professor has been saved.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Delete the professor from the saved database of user
     * @param v  Delete the Professor button
     */
    public void handleDeleteProfessor(View v){
        //find the professor to delete
        for(SavedInstructor s: savedInstructorList){
            if(s.getmInstructor().getmId() == instructorID) db.deleteSavedInstructor(s);
            Toast.makeText(this, "Professor has been deleted from saved.", Toast.LENGTH_SHORT).show();
        }
        //Move the user back to the saved professor activity
        Intent intent = new Intent(this, LoggedinSavedProfs.class);
        startActivity(intent);
    }

    /**
     * Move user back to the Student Search Activity
     * @param v back to search button
     */
    public void handleBackToSearch(View v){
        //check where the user was originating from
        Intent intent = new Intent(this, StudentSearch.class);
        if (fromActivity !=null && (fromActivity.equals("saved") || fromActivity.equals("savedSearch"))){
            intent.putExtra("FromActivity", "savedSearch");
        }
        else if (fromActivity !=null && fromActivity.equals("search")) {
            intent.putExtra("FromActivity", "search");
        }
        startActivity(intent);

    }

    /**
     * Move the usr back to the Saved Professor Activity
     * @param v back to saved button
     */
    public void handleBackToSaved(View v){
        savedInstructorList = db.getAllSavedInstructors();
        Intent intent = new Intent(this, LoggedinSavedProfs.class);
        intent.putExtra("FromActivity", "savedSearch");
        startActivity(intent);
    }

    /**
     * Move the user to the map activity
     * @param v Map button
     */
    public void handleMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("SelectedInstructor", instructor);
        startActivity(intent);
    }

    /**
     * Move the user back to the office hour activity, Instructors only
     * @param v
     */
    public void handleBackToOfficeHour(View v){
        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        intent.putExtra("SelectedInstructor", instructor);
        intent.putExtra("FromActivity", "professor");
        startActivity(intent);
    }

}
