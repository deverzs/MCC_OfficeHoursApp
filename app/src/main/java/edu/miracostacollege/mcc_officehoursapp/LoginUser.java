package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private DBHelper db;
    private List<Login> allLoginList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        //deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importInstructorsFromCSV("instructor.csv");
        db.importScheduleFromCSV("schedule.csv");
        db.importVerifiedFromCSV("verified.csv");

    }

    public void handleLoginButton(View v)
    {
        allLoginList = db.getAllLogin();
        EditText emailEditText = findViewById(R.id.emailEditText_LOGIN);
        EditText passwordEditText = findViewById(R.id.passwordEditText_LOGIN);
        boolean notValid = true;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        for (Login login : allLoginList) {
            if (email.equals(login.getmEmail())) {
                if (password.equals(login.getmPassowrd())) {
                    notValid = false;
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

        if(notValid) {
                Toast.makeText(this, "Password and email are not valid", Toast.LENGTH_LONG).show();
                emailEditText.setText("");
                passwordEditText.setText("");
        }


    }

    public void handleSearchOnly(View v)
    {
        Intent searchIntent = new Intent(this, StudentSearch.class);
        startActivity(searchIntent);
    }

    public void handleRegister(View v)
    {
        Intent register = new Intent(this, Registration.class);
        startActivity(register);
    }


}
