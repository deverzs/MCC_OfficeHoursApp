package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Login;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;
import edu.miracostacollege.mcc_officehoursapp.Model.Verification;

public class Registration extends AppCompatActivity {

    public static final String TAG = LoginUser.class.getSimpleName();

    DBHelper db;

    private List<Login> allLoginList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registration);

        db = new DBHelper(this);
        allLoginList = db.getAllLogin();

    }

    public void registerStudent(View v){

        EditText emailEditText = findViewById(R.id.emailEditText_REG);
        EditText passwordEditText = findViewById(R.id.passwordEditText_REG);
        EditText confirmPasswordEditText = findViewById(R.id.passwordConfirmEditText_REG);

        String emailUser = emailEditText.getText().toString();

        for(Login user: allLoginList){
            if(user.getmEmail().equals(emailUser)){
                Toast.makeText(this, "This email is already in use. Log in.", Toast.LENGTH_LONG).show();
                passwordEditText.setText("");
                confirmPasswordEditText.setText("");
            }
        }

        CheckBox professorOptionCheckBox = findViewById(R.id.professorOptionCheckBox_REG);

        String passwordOne = passwordEditText.getText().toString();
        String passwordTwo = confirmPasswordEditText.getText().toString();

        if(passwordOne.equals(passwordTwo)){
            if(professorOptionCheckBox.isChecked())
            {
                Intent verify = new Intent(this, ProfessorVerificationActivity.class);
                verify.putExtra("Email", emailUser);
                verify.putExtra("Password", passwordOne);
                startActivity(verify);
            } else {
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
            Toast.makeText(this, "Passwords do not match. Try again.", Toast.LENGTH_LONG).show();
            passwordEditText.setText("");
            confirmPasswordEditText.setText("");
        }


    }
}
