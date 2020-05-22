package edu.miracostacollege.mcc_officehoursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Login;
import edu.miracostacollege.mcc_officehoursapp.Model.Verification;

/**
 * The Main activity after Splash Activity
 * user logs in on this activity
 */
public class LoginUser extends AppCompatActivity {


    public static final String TAG = LoginUser.class.getSimpleName();
    private DBHelper db;

    @Override
    /**
     * Creates and inflates the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        db = new DBHelper(this);

        //keep in case of corrupted database
        //deleteDatabase(DBHelper.DATABASE_NAME);

        //check if the csv files need to be read
        if (db.getAllInstructors().size() == 0)
            db.importInstructorsFromCSV("instructor.csv");

        if (db.getAllSchedules().size() == 0)
            db.importScheduleFromCSV("schedule.csv");

        if (db.getAllVerifications().size() == 0)
            db.importVerifiedFromCSV("verified.csv");

    }

    /**
     * Logs in the user
     * @param v  Log in button
     */
    public void handleLoginButton(View v)
    {
        //get all the logins in the database
        List<Login> allLoginList = db.getAllLogin();

        //wire up views
        EditText emailEditText = findViewById(R.id.emailEditText_LOGIN);
        EditText passwordEditText = findViewById(R.id.passwordEditText_LOGIN);

        boolean notValid = true; //if the user is valid

        //Read edit texts
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //check all the logins for the user
        for (Login login : allLoginList) {
            //check if the email and password match a login
            if (email.equals(login.getmEmail())) {
                if (password.equals(login.getmPassowrd())) {
                    //check if the user is a professor
                    notValid = false;

                    //if Professor
                    if (login.getIsProfessor() == 1) {
                       //find the Instructor that is logged in
                       Instructor instructor = findInstructor(login);
                       //check for null
                       if(instructor==null){
                           notValid = true;
                       }
                       else {
                           //Move professor to the ProfessorLoggedInView
                           Intent intentProf = new Intent(this, ProfessorLoggedInView.class);
                           intentProf.putExtra("SelectedInstructor", instructor);
                           intentProf.putExtra("FromActivity", "professor");
                           startActivity(intentProf);
                       }
                    } else {
                            //student
                            Intent intentStudent = new Intent(this, LoggedinSavedProfs.class);
                            intentStudent.putExtra("FromActivity", "saved");
                            startActivity(intentStudent);
                    }
                }
            }
        }

        //Password and email do not match or not found as Instructor
        if(notValid) {
                Toast.makeText(this, "Password and email are not valid", Toast.LENGTH_LONG).show();
                emailEditText.setText("");
                passwordEditText.setText("");
        }

    }

    /**
     * Move the user to the search only activity
     * @param v Search Only button
     */
    public void handleSearchOnly(View v)
    {
        Intent searchIntent = new Intent(this, StudentSearch.class);
        searchIntent.putExtra("FromActivity", "search");
        startActivity(searchIntent);
    }

    /**
     * Move the user to the register login
     * @param v  Text View to register
     */
    public void handleRegister(View v)
    {
        Intent register = new Intent(this, Registration.class);
        startActivity(register);
    }

    /**
     * Find the instructor for loggin in
     * @param login The login being used
     * @return Instructor to move to ProfessorLoggedInView
     */
    public Instructor findInstructor(Login login){
        //Check the verification table, crossed with the instructor
        List<Verification> verify = db.getAllVerifications();
        List<Instructor> instructors = db.getAllInstructors()  ;

       for(Verification v: verify){
           if(login.getmEmail().equals(login.getmEmail())){
               if(v.ismIsVerified()==1){
                   for(Instructor i: instructors){
                       if(v.getmFirstName().equals(i.getmFirstName())){
                               if(v.getmLastName().equals(i.getmLastName())) {
                                   return i;
                               }
                       }
                   }
               }
           }
       }
       return null; //didn't find the professor

    }
}
