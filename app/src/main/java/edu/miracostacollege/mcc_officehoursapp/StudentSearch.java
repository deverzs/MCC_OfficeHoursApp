package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;

/**
 * Student Search for an Instructor
 */
public class StudentSearch extends AppCompatActivity {

    public static final String TAG = StudentSearch.class.getSimpleName();
    private DBHelper db;

    private List<Instructor> allInstructorsList;
    private professorsListViewAdapter instructorListAdapter;
    private Spinner instructorSpinner_SEARCH;
    private EditText instructorName_SEARCH;

    @Override
    /**
     * Create and inflate activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        db = new DBHelper(this);

        //wire up Login Button and set to visible if not logged in already
        Button logIn = findViewById(R.id.logInButton_SEARCH);
        Intent checkIntent = getIntent();
        String check = checkIntent.getStringExtra("FromActivity");
        if(check != null && (check.equals("savedSearch") || check.equals("professor"))){
            logIn.setVisibility(View.INVISIBLE);
        }

        //populate the list from the database
        allInstructorsList = db.getAllInstructors();
        List<Instructor> filteredInstructorsList = db.getAllInstructors();

        //wire up the views
        instructorSpinner_SEARCH = findViewById(R.id.instructorSpinner_SEARCH);
        ListView professorListView_SEARCH = findViewById(R.id.professorsListView_SEARCH);
        instructorName_SEARCH = findViewById(R.id.instructorName_SEARCH);

        //set Text Watcher
        instructorName_SEARCH.addTextChangedListener(instructorNameTextWatcher);

        //set list adapter
        instructorListAdapter = new professorsListViewAdapter(this,
                R.layout.activity_professor_list_item, filteredInstructorsList);
        professorListView_SEARCH.setAdapter(instructorListAdapter);

        //spinner adapter
        final ArrayAdapter<String> instructorSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getInstructorNames());
        instructorSpinner_SEARCH.setAdapter(instructorSpinnerAdapter);

        instructorSpinner_SEARCH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)  {
                    reset(view);
                }
                else  {
                    String selectedName = String.valueOf(parent.getItemAtPosition(position));
                    instructorListAdapter.clear();
                    for(int i = 0; i < allInstructorsList.size(); i++)  {
                        if(allInstructorsList.get(i).getmFullName().equals(selectedName))
                            instructorListAdapter.add(allInstructorsList.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * Get the Instructor Names to populate the spinner
     * @return string array of names
     */
    public String[] getInstructorNames()  {
        String[] names = new String[allInstructorsList.size() + 1];
        names[0] = "[SELECT INSTRUCTOR]";
        for(int i = 1; i < names.length; i++)  {
            names[i] = allInstructorsList.get(i - 1).getmFullName();
        }
        return names;
    }

    /**
     * Reset the list
     * @param v  reset button
     */
    public void reset(View v)
    {
        instructorSpinner_SEARCH.setSelection(0);
        instructorListAdapter.clear();
        instructorName_SEARCH.setText("");
        for(Instructor i: allInstructorsList) {
            instructorListAdapter.add(i);
        }
    }


    /**
     * Text Watcher set
     */
    public TextWatcher instructorNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        /**
         * Watch for the text change for instructor name
         */
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String cleanText = s.toString().toLowerCase();
            if(!cleanText.isEmpty()) {
                instructorListAdapter.clear();
                for(Instructor i: allInstructorsList)  {
                    if(i.getmFullName().toLowerCase().contains(cleanText))  {
                        instructorListAdapter.add(i);
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    /**
     * Move the user to the Professor Details page, depending on Instructor picked
     * @param v   instructor selected from list view
     */
    public void viewProfessorsSchedule(View v)  {
        //check the activity originally from
        Intent checkIntent = getIntent();
        String check = checkIntent.getStringExtra("FromActivity");

        //get the tag of the instructor picked
        Instructor selectedInstructor =   (Instructor) v.getTag();
        finish();
        Intent intent = new Intent(this, ProfessorDetails.class);

        //depending on where user came from, move user with the proper string
        intent.putExtra("SelectedInstructor", selectedInstructor);
        if(check != null &&
                (check.equals("registered") ||  check.equals("professor"))) {
           intent.putExtra("FromActivity", "saved");
        }
        else if (check != null && check.equals("search")) {
            intent.putExtra("FromActivity", "search");
        }
        else if(check != null && check.equals("savedSearch")){
            intent.putExtra("FromActivity", "savedSearch");
        }
        startActivity(intent);
    }


    /**
     * Log user out by moving to the login page
     * @param v  Logout button
     */
    public void handleLogIn(View v){
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }
}