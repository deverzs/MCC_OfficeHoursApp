package edu.miracostacollege.mcc_officehoursapp.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //checking merge with Sarah
    private Context mContext;

    //DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    public static final String DATABASE_NAME = "OfficeHours";
    private static final int DATABASE_VERSION = 1;

    //FIELDS (COLUMN NAMES) FOR THE INSTRUCTOR TABLE
    private static final String INSTRUCOTR_TABLE = "Instructor";
    private static final String INSTRUCTOR_KEY_FIELD_ID = "_id";
    private static final String INSTRUCTOR_INSTR_CODE = "code";
    private static final String INSTRUCTOR_NAME = "name";
    private static final String INSTRUCTOR_PHONE = "phone";
    public static final String INSTRUCTOR_OFFICE = "office";
    public static final String INSTRUCTOR_APPOINTMENT = "appointment";


    //FIELDS (COLUMN NAMES) FOR THE SCHEDULE TABLE
    public static final String SCHEDULE_TABLE = "Schedule";
    public static final String SCHEDULE_KEY_FIELD_ID = "_id";
    public static final String SCHEDULE_INSTR_CODE = "code";
    public static final String SCHEDULE_SECTION = "section";
    public static final String SCHEDULE_DAY = "day";
    public static final String SCHEDULE_TIME = "time";
    public static final String SCHEDULE_LOCATION = "location";

    //FIELDS (COLUMN NAMES) FOR THE STATUS TABLE
    public static final String STATUS_TABLE = "Status";
    public static final String STATUS_KEY_FIELD_ID = "_id";
    public static final String STATUS_INSTR_CODE = "code";
    public static final String STATUS_SECTION = "section";
    public static final String STATUS_DAY = "day";
    public static final String STATUS_STATUS = "status";

    //FIELDS (COLUMN NAMES) FOR THE LOGIN TABLE
    public static final String LOGIN_TABLE = "Login";
    public static final String LOGIN_KEY_FIELD_ID = "_id";
    public static final String LOGIN_INSTR_CODE = "email";
    public static final String LOGIN_SECTION = "password";
    public static final String LOGIN_PROFESSOR = "professor";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
