package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Login;

/**
 * Registration activity for a Student
 */
public class Registration extends AppCompatActivity {

    public static final String TAG = LoginUser.class.getSimpleName();

    DBHelper db;

    //Animation added here
    private List<Login> allLoginList;
    private Animation zoom;
    private ImageView head;

    @Override
    /**
     * Create and inflate the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registration);

        db = new DBHelper(this);
        allLoginList = db.getAllLogin();

        //animate the Spartan head
        head = findViewById(R.id.spartaImageView_REG);
        zoom = AnimationUtils.loadAnimation(this, R.anim.zoom_anim);
        head.startAnimation(zoom);
    }

    /**
     * Register a student
     * @param v  Register button
     */
    public void registerStudent(View v){
        //wire up the edit texts
        EditText emailEditText = findViewById(R.id.emailEditText_REG);
        EditText passwordEditText = findViewById(R.id.passwordEditText_REG);
        EditText confirmPasswordEditText = findViewById(R.id.passwordConfirmEditText_REG);

        //get email and check if it is already in use
        String emailUser = emailEditText.getText().toString();
        for(Login user: allLoginList){
            if(user.getmEmail().equals(emailUser)){
                Toast.makeText(this, "This email is already in use. Log in.", Toast.LENGTH_LONG).show();
                passwordEditText.setText("");
                confirmPasswordEditText.setText("");
            }
        }
        //record whether the checkbox for Professor option is checked
        CheckBox professorOptionCheckBox = findViewById(R.id.professorOptionCheckBox_REG);

        //get the password and check if the same
        String passwordOne = passwordEditText.getText().toString();
        String passwordTwo = confirmPasswordEditText.getText().toString();
        if(passwordOne.equals(passwordTwo)){

            //if the professor checkbox is marked
            if(professorOptionCheckBox.isChecked())
            {
                //Move user to get verified
                Intent verify = new Intent(this, ProfessorVerificationActivity.class);
                verify.putExtra("Email", emailUser);
                verify.putExtra("Password", passwordOne);
                startActivity(verify);
            } else {
                //Create a new Student user and move user to the LoggedInSaveProfs activity
                Login addUser = new Login(emailUser, passwordOne, 0);
                db.addLogin(addUser);
                Toast.makeText(this, "You have successfully created an account.",
                        Toast.LENGTH_LONG).show();
                Intent searchIntent = new Intent(this, LoggedinSavedProfs.class);
                searchIntent.putExtra("FromActivity", "saved");
                startActivity(searchIntent);
            }
        }
        else {
            //the password and login do not match
            Toast.makeText(this, "Passwords do not match. Try again.", Toast.LENGTH_LONG).show();
            passwordEditText.setText("");
            confirmPasswordEditText.setText("");
        }
    }
}
