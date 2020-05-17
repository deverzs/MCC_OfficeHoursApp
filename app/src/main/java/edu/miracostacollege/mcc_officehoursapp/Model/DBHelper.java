package edu.miracostacollege.mcc_officehoursapp.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //testing add to Sarah
    private Context mContext;

    //DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    public static final String DATABASE_NAME = "OfficeHours";
    private static final int DATABASE_VERSION = 1;

    //FIELDS (COLUMN NAMES) FOR THE INSTRUCTOR TABLE
    private static final String INSTRUCTOR_TABLE = "Instructor";
    private static final String INSTRUCTOR_KEY_FIELD_ID = "_id";
    private static final String INSTRUCTOR_INSTR_CODE = "code";
    private static final String INSTRUCTOR_FIRST_NAME = "firstName";
    private static final String INSTRUCTOR_LAST_NAME = "lastName";
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
    public static final String LOGIN_INSTR_EMAIL = "email";
    public static final String LOGIN_PASSWORD = "password";

    //FIELDS (COLUMN NAMES) FOR THE VERIFICATION TABLE
    public static final String VERIFICATION_TABLE = "Verification";
    public static final String VERIFICATION_KEY_FIELD_ID = "_id";
    public static final String VERIFICATION_EMAIL = "email";
    public static final String VERIFICATION_PIN = "pin";
    public static final String VERIFICATION_LAST_NAME = "lastName";
    public static final String VERIFICATION_FIRST_NAME = "firstName";
    public static final String VERIFICATION_IS_VERIFIED = "isVerified";

    //FIELDS (COLUMN NAMES) FOR THE SAVED INSTRUCTOR  TABLE
    public static final String SAVED_TABLE = "SavedInstructor";
    public static final String SAVED_KEY_FIELD_ID = "_id";
    public static final String SAVED_INSTR_CODE = "code";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String instructorTable = "CREATE TABLE IF NOT EXISTS " + INSTRUCTOR_TABLE + " ("
                + INSTRUCTOR_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + INSTRUCTOR_INSTR_CODE + " INTEGER, "
                + INSTRUCTOR_LAST_NAME + " TEXT, "
                + INSTRUCTOR_FIRST_NAME + " TEXT, "
                + INSTRUCTOR_PHONE + " TEXT, "
                + INSTRUCTOR_OFFICE + " TEXT, "
                + INSTRUCTOR_APPOINTMENT + " INTEGER" + ") ";
        db.execSQL(instructorTable);

        String scheduleTable = "CREATE TABLE IF NOT EXISTS " + SCHEDULE_TABLE + " ("
                + SCHEDULE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + SCHEDULE_INSTR_CODE + " INTEGER, "
                + SCHEDULE_SECTION + " INTEGER, "
                + SCHEDULE_DAY + " INTEGER, "
                + SCHEDULE_TIME + " TEXT, "
                + SCHEDULE_LOCATION + " TEXT" + ")";
        db.execSQL(scheduleTable);

        String loginTable = "CREATE TABLE IF NOT EXISTS " + LOGIN_TABLE + " ("
                + LOGIN_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + LOGIN_INSTR_EMAIL + " TEXT, "
                + LOGIN_PASSWORD + " TEXT" + ")";
        db.execSQL(loginTable);

        String statusTable = "CREATE TABLE IF NOT EXISTS " + STATUS_TABLE + " ("
                + STATUS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + STATUS_INSTR_CODE + " INTEGER, "
                + STATUS_SECTION + " INTEGER, "
                + STATUS_DAY + " INTEGER, "
                + STATUS_STATUS + " INTEGER, "
                + "FOREIGN KEY(" + STATUS_INSTR_CODE + ")REFERENCES "
                + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_INSTR_CODE + "),"
                + "FOREIGN KEY(" + STATUS_SECTION + ")REFERENCES "
                + SCHEDULE_TABLE + "(" + SCHEDULE_SECTION + "),"
                + "FOREIGN KEY(" + STATUS_DAY + ")REFERENCES "
                + SCHEDULE_TABLE + "(" + SCHEDULE_DAY + "))";
        db.execSQL(statusTable);

        String verificationTable = "CREATE TABLE IF NOT EXISTS " + VERIFICATION_TABLE + " ("
                + VERIFICATION_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + VERIFICATION_EMAIL + " TEXT, "
                + VERIFICATION_PIN + " INTEGER, "
                + VERIFICATION_LAST_NAME + " TEXT, "
                + VERIFICATION_FIRST_NAME + " TEXT, "
                + VERIFICATION_IS_VERIFIED + " INTEGER, "
                + "FOREIGN KEY(" + VERIFICATION_LAST_NAME + ")REFERENCES "
                + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_LAST_NAME + "),"
                + "FOREIGN KEY(" + VERIFICATION_FIRST_NAME + ")REFERENCES "
                + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_FIRST_NAME + "),"
                + "FOREIGN KEY(" + VERIFICATION_EMAIL + ")REFERENCES "
                + LOGIN_TABLE + "(" + LOGIN_INSTR_EMAIL + "))";
        db.execSQL(verificationTable);

        String savedTable = "CREATE TABLE IF NOT EXISTS " + SAVED_TABLE + " ("
                + SAVED_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + SAVED_INSTR_CODE + " INTEGER, "
                + "FOREIGN KEY(" + SAVED_INSTR_CODE + ")REFERENCES "
                + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_INSTR_CODE + "))";
        db.execSQL(savedTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + INSTRUCTOR_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + STATUS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + VERIFICATION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + SAVED_TABLE);
            onCreate(db);

    }
}
