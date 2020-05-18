package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Login;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;
import edu.miracostacollege.mcc_officehoursapp.Model.Verification;

public class LoginUser extends AppCompatActivity {


    public static final String TAG = LoginUser.class.getSimpleName();

    //not sure if add here or only in activity that needs it? or on startup of app?
    private DBHelper db;
    private List<Instructor> allInstructorList;
    private List<Schedule> allScheduleList;
    private List<Login> allLoginList;
    private  List<Verification> allVerificationList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importInstructorsFromCSV("instructor.csv");
        db.importScheduleFromCSV("schedule.csv");
        db.importVerifiedFromCSV("verified.csv");

        allInstructorList = db.getAllInstructors();
        allScheduleList = db.getAllSchedules();
        allVerificationList = db.getAllVerifications();
        allLoginList = db.getAllLogin();

    }

    public void handleButtons(View v)
    {
        Button button = (Button) v;
        Button searchOnlyButton = findViewById(R.id.searchOnlyButton_LOGIN);
        Button loginButton = findViewById(R.id.loginButton_LOGIN);
        EditText emailEditText = findViewById(R.id.emailEditText_LOGIN);
        EditText passwordEditText = findViewById(R.id.passwordEditText_LOGIN);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(button == loginButton) {
            for (Login login : allLoginList) {
                if (email.equals(login.getmEmail())) {
                    if (password.equals(login.getmPassowrd())) {
                        if (login.getIsProfessor() == 1) {
                            //professor
                            Intent intentProf = new Intent(this, ProfessorLoggedInView.class);
                            startActivity(intentProf);
                        } else {
                            //student
                            Intent intentStudent = new Intent(this, LoggedinSavedProfs.class);
                            startActivity(intentStudent);
                        }
                    }

                }

            }
            Toast.makeText(this, "Password and email do not match", Toast.LENGTH_LONG).show();
            emailEditText.setText("");
            passwordEditText.setText("");
        } else if(button == searchOnlyButton){
            Intent searchIntent = new Intent(this, StudentSearch.class);
            startActivity(searchIntent);

        }

    }

    public void handleRegister(View v)
    {
        Intent register = new Intent(this, Registration.class);
        startActivity(register);
    }


}
