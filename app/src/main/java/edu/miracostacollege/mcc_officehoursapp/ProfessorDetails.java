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
    private List<Schedule> alSchedulesList ;
    private List<Schedule> selectedScheduleList ;
    private List<SavedInstructor> savedInstructorList ;
    private List<Instructor> allSavedInstructorList;
    private ScheduleListAdapter scheduleListAdapter;
    private ListView scheduleListView;
    private DBHelper db;
    private Instructor instructor;
    private long instructorID;
    private Button saveProfessorBuutton;
    private Button deleteProfessorButton;
    private Button backToSavedButton;
    private Button backToSearchButton;
    private Button backtoOfficeHours;
    private String fromActivity;
    private TextView professorNameTextView;
    private TextView professorPhoneTextView;
    private TextView availableByApptTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_details);
        professorNameTextView  = findViewById(R.id.professorNameTextView_DETAILS);
        professorPhoneTextView = findViewById(R.id.extensionTextView_DETAILS);
        saveProfessorBuutton = findViewById(R.id.saveProfessorButton_DETAILS);
        deleteProfessorButton = findViewById(R.id.deleteProfessorButton_DETAILS);
        backToSearchButton = findViewById(R.id.backToSearchButton_DETAILS);
        backToSavedButton = findViewById(R.id.backToSavedButton_DETAILS);
        availableByApptTextView = findViewById(R.id.availableByApptTextView_DETAILS);
        backtoOfficeHours = findViewById(R.id.returnToOHButton_DETAILS);

        db = new DBHelper(this);

        alSchedulesList = db.getAllSchedules();
        savedInstructorList = db.getAllSavedInstructors();
        allSavedInstructorList = db.getAllInstructors();
        selectedScheduleList = new ArrayList<>();


        Intent intent = getIntent();
        instructor = intent.getParcelableExtra("SelectedInstructor");
        Log.i(TAG, "//Selected, Details: " + instructor.getmFullName());
        fromActivity = intent.getStringExtra("FromActivity");
        Log.i(TAG, "//From intent: " + fromActivity);
        instructorID = instructor.getmId();

        professorNameTextView.setText(instructor.getmFullName());
        professorPhoneTextView.setText(instructor.getmPhone());
        String temp = "Available by Appointment";
        if(instructor.byAppointment()==1) availableByApptTextView.setText(temp);
        else availableByApptTextView.setText("");

        selectedScheduleList.clear();

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
            Toast.makeText(this, "That Professor is already saved.", Toast.LENGTH_SHORT).show();
        }
        else  {
            db.addSavedInstructor(instructorID);
            savedInstructorList = db.getAllSavedInstructors();
            Log.i(TAG, "//DETAILS. NEW SIZE " + savedInstructorList.size());
            savedInstructorList = db.getAllSavedInstructors();
            Toast.makeText(this, "Professor has been saved.", Toast.LENGTH_SHORT).show();
        }

    }

    public void handleDeleteProfessor(View v){
        for(SavedInstructor s: savedInstructorList){
            if(s.getmInstructor().getmId() == instructorID) db.deleteSavedInstructor(s);
            Toast.makeText(this, "Professor has been deleted from saved.", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, LoggedinSavedProfs.class);
        startActivity(intent);
    }

    public void handleBackToSearch(View v){
        Intent intent = new Intent(this, StudentSearch.class);
        if (fromActivity !=null && (fromActivity.equals("saved") || fromActivity.equals("savedSearch"))){
            intent.putExtra("FromActivity", "savedSearch");
        }
        else if (fromActivity !=null && fromActivity.equals("search")) {
            intent.putExtra("FromActivity", "search");
        }
        startActivity(intent);

    }

    public void handleBackToSaved(View v){
        savedInstructorList = db.getAllSavedInstructors();
        Log.i(TAG, "//Handle SAVED. NEW SIZE " + savedInstructorList.size());
        Intent intent = new Intent(this, LoggedinSavedProfs.class);
        intent.putExtra("FromActivity", "savedSearch"); //added need????
        startActivity(intent);
    }

    public void handleMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("SelectedInstructor", instructor);
        startActivity(intent);
    }

    public void handleBackToOfficeHour(View v){
        Intent intent = new Intent(this, ProfessorLoggedInView.class);
        intent.putExtra("SelectedInstructor", instructor);
        intent.putExtra("FromActivity", "professor");
        startActivity(intent);
    }

}
