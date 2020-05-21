package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Login;
import edu.miracostacollege.mcc_officehoursapp.Model.Verification;

public class ProfessorVerificationActivity extends AppCompatActivity {

    public static final String TAG = ProfessorLoggedInView.class.getSimpleName();
    private EditText professorFirstNameEditText_VERIFY;
    private EditText professorLastNameEditText_VERIFY;
    private EditText pinEditText_VERIFY;

    private DBHelper db;

    private List<Verification> allVerificationsList;
    private List<Instructor> allInstructorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_verification);

        db = new DBHelper(this);

        allInstructorsList = db.getAllInstructors();

        professorFirstNameEditText_VERIFY = findViewById(R.id.professorFirstNameEditText_VERIFY);
        professorLastNameEditText_VERIFY = findViewById(R.id.professorLastNameEditText_VERIFY);
        pinEditText_VERIFY = findViewById(R.id.pinEditText_VERIFY);

        allVerificationsList = db.getAllVerifications();
    }


    public void verify(View v) {

        String firstName = professorFirstNameEditText_VERIFY.getText().toString().toLowerCase();
        String lastName = professorLastNameEditText_VERIFY.getText().toString().toLowerCase();
        int pin = Integer.parseInt(pinEditText_VERIFY.getText().toString());

        boolean notValid = true;
        for (Verification verify : allVerificationsList) {
            Log.e(TAG, "//" + verify.toString());
            if (firstName.equalsIgnoreCase(verify.getmFirstName()) &&
                    lastName.equalsIgnoreCase(verify.getmLastName()) && pin == verify.getmPin()) {
                notValid = false;

                for (Instructor i : allInstructorsList) {
                    if (i.getmFirstName().equals(verify.getmFirstName()) &&
                            i.getmLastName().equalsIgnoreCase(verify.getmLastName())) {
                        verify.setmIsVerified(true);
                        Log.i(TAG, "//verified change "+ verify.getIsVerified());
                        db.updateVerification(verify);
                        Intent verifyPassed = getIntent();
                        String emailUser = verifyPassed.getStringExtra("Email");
                        String passwordOne = verifyPassed.getStringExtra("Password");
                        Login addUser = new Login(emailUser, passwordOne, 1);
                        db.addLogin(addUser);
                        finish();
                        Intent intent = new Intent(this, ProfessorLoggedInView.class);
                        intent.putExtra("SelectedInstructor", i);
                        startActivity(intent);
                    }
                }
            }
        }
        if (notValid) {

            Toast.makeText(this, "First or last name or pin are not valid", Toast.LENGTH_LONG).show();
            professorLastNameEditText_VERIFY.setText("");
            professorFirstNameEditText_VERIFY.setText("");
            pinEditText_VERIFY.setText("");

        }
    }
}