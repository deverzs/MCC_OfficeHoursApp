package edu.miracostacollege.mcc_officehoursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;

public class Login extends AppCompatActivity {

    public static final String TAG = Login.class.getSimpleName();

    //not sure if add here or only in activity that needs it? or on startup of app?
    private DBHelper db;
    private List<Instructor> instructorList;


    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button searchOnlyButton;
    private TextView registerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logIn(View v)
    {
        emailEditText = findViewById(R.id.emailEditText_LOGIN);
        passwordEditText = findViewById(R.id.passwordEditText_LOGIN);


    }


}
