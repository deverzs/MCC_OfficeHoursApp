package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.professorsListViewAdapter;

//Checking changes after commit issues
public class StudentSearch extends AppCompatActivity {

    private DBHelper db;

    private List<Instructor> allInstructorsList;
    private List<Instructor> filteredInstructorsList;

    private ListView professorListView_SEARCH;
    private professorsListViewAdapter instructorListAdapter;
    private Spinner instructorSpinner_SEARCH;
    private EditText instructorName_SEARCH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        db = new DBHelper(this);
        db.importInstructorsFromCSV("instructor.csv");

        allInstructorsList = db.getAllInstructors();

        instructorSpinner_SEARCH = findViewById(R.id.instructorSpinner_SEARCH);
        professorListView_SEARCH = findViewById(R.id.professorsListView_SEARCH);
        instructorName_SEARCH = findViewById(R.id.instructorName_SEARCH);
        instructorName_SEARCH.addTextChangedListener(instructorNameTextWatcher);

        instructorListAdapter = new professorsListViewAdapter(this, R.layout.activity_professor_list_item, filteredInstructorsList );
        professorListView_SEARCH.setAdapter(instructorListAdapter);

        final ArrayAdapter<String> instructorSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getInstructorNames());
        instructorSpinner_SEARCH.setAdapter(instructorSpinnerAdapter);

        instructorSpinner_SEARCH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    reset(view);
                    return;
                }
                else
                {
                    String selectedName = String.valueOf(parent.getItemAtPosition(position));
                    instructorListAdapter.clear();
                    for(int i = 0; i < allInstructorsList.size(); i++)
                    {
                        if(allInstructorsList.get(i).getmFullName().equals(selectedName))
                            instructorListAdapter.add(allInstructorsList.get(i));

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public String[] getInstructorNames()
    {
        String[] names = new String[allInstructorsList.size() + 1];
        names[0] = "[SELECT INSTRUCTOR]";
        for(int i = 1; i < names.length; i++)
        {
            names[i] = allInstructorsList.get(i - 1).getmFullName();
        }
        return names;
    }

    public void reset(View v)
    {
        instructorSpinner_SEARCH.setSelection(0);
        instructorListAdapter.clear();
        instructorName_SEARCH.setText("");
        for(Instructor i: allInstructorsList)
        {
            instructorListAdapter.add(i);
        }
    }


    public TextWatcher instructorNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String cleanText = s.toString().toLowerCase();
            if(!cleanText.isEmpty())
            {
                instructorListAdapter.clear();
                for(Instructor i: allInstructorsList)
                {
                    if(i.getmFullName().toLowerCase().contains(cleanText))
                    {
                        instructorListAdapter.add(i);
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}