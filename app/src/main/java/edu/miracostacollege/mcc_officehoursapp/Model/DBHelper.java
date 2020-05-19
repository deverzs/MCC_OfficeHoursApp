package edu.miracostacollege.mcc_officehoursapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    public static final String DATABASE_NAME = "OfficeHours";
    private static final int DATABASE_VERSION = 1;

    //FIELDS (COLUMN NAMES) FOR THE INSTRUCTOR TABLE
    private static final String INSTRUCTOR_TABLE = "Instructor";
    private static final String INSTRUCTOR_KEY_FIELD_ID = "_id";
    //private static final String INSTRUCTOR_INSTR_CODE = "code";
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
    public static final String STATUS_INSTR_CODE = "code_status"; //instructor code
    public static final String STATUS_SECTION = "section_status"; //section of day
    public static final String STATUS_DAY = "day_status";  //which day
    public static final String STATUS_TIME = "time_status";  //which time
    public static final String STATUS_LOCATION = "location_status";  //which location
    public static final String STATUS_STATUS = "status_status"; //canceled or not

    //FIELDS (COLUMN NAMES) FOR THE LOGIN TABLE
    public static final String LOGIN_TABLE = "Login_Table";
    //public static final String LOGIN_KEY_FIELD_ID = "_id";
    public static final String LOGIN_EMAIL = "email_login";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGIN_IS_VERIFIED = "verifiedProfessor";

    //FIELDS (COLUMN NAMES) FOR THE VERIFICATION TABLE
    public static final String VERIFICATION_TABLE = "Verification";
    public static final String VERIFICATION_KEY_FIELD_ID = "_id";
    public static final String VERIFICATION_EMAIL = "email_verify";
    public static final String VERIFICATION_PIN = "pin";
    public static final String VERIFICATION_LAST_NAME = "lastName_verify";
    public static final String VERIFICATION_FIRST_NAME = "firstName_verify";
    public static final String VERIFICATION_IS_VERIFIED = "isVerified";

    //FIELDS (COLUMN NAMES) FOR THE SAVED INSTRUCTOR  TABLE
    public static final String SAVED_TABLE = "SavedInstructor";
    //public static final String SAVED_KEY_FIELD_ID = "_id";
    public static final String SAVED_INSTR_CODE = "code_saved";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String instructorTable = "CREATE TABLE IF NOT EXISTS " + INSTRUCTOR_TABLE + " ("
                + INSTRUCTOR_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
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
                + LOGIN_EMAIL + " TEXT PRIMARY KEY, "
                + LOGIN_PASSWORD + " TEXT, "
                + LOGIN_IS_VERIFIED + " INTEGER" + ")";
        db.execSQL(loginTable);

        String statusTable = "CREATE TABLE IF NOT EXISTS " + STATUS_TABLE + " ("
                + STATUS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, " //schedule ID
                + STATUS_STATUS + " INTEGER, "                  // boolean rep for canceled or not
                + "FOREIGN KEY(" + STATUS_KEY_FIELD_ID + ")REFERENCES "
                + SCHEDULE_TABLE + "(" + SCHEDULE_KEY_FIELD_ID + "))";
        db.execSQL(statusTable);
        /*
        String statusTable = "CREATE TABLE IF NOT EXISTS " + STATUS_TABLE + " ("
                + STATUS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + STATUS_INSTR_CODE + " INTEGER, "
                + STATUS_SECTION + " INTEGER, "
                + STATUS_DAY + " INTEGER, "
                + STATUS_TIME + " INTEGER, "
                + STATUS_LOCATION + " TEXT, "
                + STATUS_STATUS + " INTEGER, "
                + "FOREIGN KEY(" + STATUS_KEY_FIELD_ID + ")REFERENCES "
                + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_KEY_FIELD_ID + "),"
                + "FOREIGN KEY(" + STATUS_SECTION + ")REFERENCES "
                + SCHEDULE_TABLE + "(" + SCHEDULE_SECTION + "),"
                + "FOREIGN KEY(" + STATUS_DAY + ")REFERENCES "
                + SCHEDULE_TABLE + "(" + SCHEDULE_DAY + "))";
        db.execSQL(statusTable);
        */
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
                + LOGIN_TABLE + "(" + LOGIN_EMAIL + "))";
        db.execSQL(verificationTable);

        String savedTable = "CREATE TABLE IF NOT EXISTS " + SAVED_TABLE + " ("
                + SAVED_INSTR_CODE + " INTEGER, "
                + "FOREIGN KEY(" + SAVED_INSTR_CODE + ")REFERENCES "
                + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_KEY_FIELD_ID + "))";
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

    //************************* INSTRUCTOR TABLE OPERATIONS **************

    public void addInstructor(Instructor instructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(INSTRUCTOR_LAST_NAME, instructor.getmLastName());
        values.put(INSTRUCTOR_FIRST_NAME, instructor.getmFirstName());
        values.put(INSTRUCTOR_PHONE, instructor.getmPhone());
        values.put(INSTRUCTOR_OFFICE, instructor.getmOfficeRoomNumber());
        values.put(INSTRUCTOR_APPOINTMENT, instructor.byAppointment());

        long id = db.insert(INSTRUCTOR_TABLE, null, values);
       // instructor.setmId(id);
        db.close();

    }


    public List<Instructor> getAllInstructors()
    {
        List<Instructor> instructorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                INSTRUCTOR_TABLE,
                new String[] {INSTRUCTOR_KEY_FIELD_ID, INSTRUCTOR_LAST_NAME,
                        INSTRUCTOR_FIRST_NAME, INSTRUCTOR_PHONE, INSTRUCTOR_OFFICE,
                        INSTRUCTOR_APPOINTMENT} ,
                null,
                null,
                null,
                null,
                null,
                null);


        if(cursor.moveToFirst())
        {
            do {
                Instructor instructor =
                        new Instructor(
                                cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getInt(5));
                                instructorList.add(instructor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return instructorList;

    }

    public int deleteInstructor(Instructor instructor)
    {
        if (instructor == null)
        {
            return 0;
        }
        SQLiteDatabase db = this.getWritableDatabase();

        int deleteRow = db.delete(INSTRUCTOR_TABLE, INSTRUCTOR_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(instructor.getmId())});

        db.close();
        return deleteRow;
    }

    public void deleteAllInstructors()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(INSTRUCTOR_TABLE, null, null);
        db.close();

    }

    public void updateInstructor(Instructor instructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(INSTRUCTOR_LAST_NAME, instructor.getmLastName());
        values.put(INSTRUCTOR_FIRST_NAME, instructor.getmFirstName());
        values.put(INSTRUCTOR_PHONE, instructor.getmPhone());
        values.put(INSTRUCTOR_OFFICE, instructor.getmOfficeRoomNumber());
        values.put(INSTRUCTOR_APPOINTMENT, instructor.byAppointment());

        db.update(INSTRUCTOR_TABLE, values, INSTRUCTOR_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(instructor.getmId())});
        db.close();
    }

    public Instructor getInstructor(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                INSTRUCTOR_TABLE,
                new String[] {INSTRUCTOR_KEY_FIELD_ID, INSTRUCTOR_LAST_NAME,
                        INSTRUCTOR_FIRST_NAME, INSTRUCTOR_PHONE, INSTRUCTOR_OFFICE,
                        INSTRUCTOR_APPOINTMENT},
                INSTRUCTOR_KEY_FIELD_ID + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null);

        if(cursor != null) cursor.moveToFirst();

        Instructor instructor = new Instructor (cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5));
        cursor.close();
        db.close();
        return instructor;

    }

    //************************* SCHEDULE TABLE OPERATIONS **************

    public void addSchedule(Schedule schedule)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHEDULE_INSTR_CODE, schedule.getmInstructor().getmId());
        values.put(SCHEDULE_SECTION, schedule.getmOfficeHourSection());
        values.put(SCHEDULE_DAY, schedule.getmOfficeHourDay());
        values.put(SCHEDULE_TIME, schedule.getmOfficeHourTime());
        values.put(SCHEDULE_LOCATION, schedule.getmOfficeHourLocation());

        long id = db.insert(SCHEDULE_TABLE, null, values);
        schedule.setmId(id);
        db.close();
    }

    public List<Schedule> getAllSchedules()
    {
        List<Schedule> scheduleList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SCHEDULE_TABLE,
                new String[] {SCHEDULE_KEY_FIELD_ID, SCHEDULE_INSTR_CODE, SCHEDULE_SECTION,
                        SCHEDULE_DAY, SCHEDULE_TIME, SCHEDULE_LOCATION} ,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Schedule schedule =
                        new Schedule(cursor.getLong(0),
                                getInstructor(cursor.getInt(1)),
                                cursor.getInt(2),
                                cursor.getInt(3),
                                cursor.getString(4),
                                cursor.getString(5));
                scheduleList.add(schedule);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scheduleList;
    }

    public int deleteSchedule(Schedule schedule)
    {
        if(schedule == null)
        {
            return 0;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        int deleteRow = db.delete(SCHEDULE_TABLE, SCHEDULE_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(schedule.getmInstructor().getmId())});
        db.close();
        return deleteRow;
    }

    public void deleteAllSchedules()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SCHEDULE_TABLE, null, null);
        db.close();
    }

    public void updateSchedule(Schedule schedule)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHEDULE_INSTR_CODE, schedule.getmInstructor().getmId());
        values.put(SCHEDULE_SECTION, schedule.getmOfficeHourSection());
        values.put(SCHEDULE_DAY, schedule.getmOfficeHourDay());
        values.put(SCHEDULE_TIME, schedule.getmOfficeHourTime());
        values.put(SCHEDULE_LOCATION, schedule.getmOfficeHourLocation());

        db.update(SCHEDULE_TABLE, values, SCHEDULE_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(schedule.getmInstructor().getmId())});

        db.close();

    }

   public Schedule getSchedule(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SCHEDULE_TABLE,
                new String[] {SCHEDULE_KEY_FIELD_ID, SCHEDULE_INSTR_CODE, SCHEDULE_SECTION,
                        SCHEDULE_DAY, SCHEDULE_TIME, SCHEDULE_LOCATION},
                SCHEDULE_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(id)},
                null, null, null, null );

        if(cursor != null)  cursor.moveToFirst();

        Schedule schedule = new Schedule(cursor.getLong(0),
                    getInstructor(cursor.getInt(1)),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5));
        cursor.close();
        db.close();
        return schedule;
    }


    //************************* STATUS TABLE OPERATIONS **************

    public void addStatus(Status status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(STATUS_KEY_FIELD_ID, status.getmSchedule().getmId());
        //values.put(STATUS_SECTION, status.getmSchedule().getmOfficeHourSection());
        //values.put(STATUS_DAY, status.getmSchedule().getmOfficeHourDay());
        //values.put(STATUS_TIME, status.getmSchedule().getmOfficeHourTime());
        //values.put(STATUS_LOCATION, status.getmSchedule().getmOfficeHourLocation());
        values.put(STATUS_STATUS, status.getmStatus());

        long id = db.insert(STATUS_TABLE, null, values);
        status.setmId(id);
        db.close();

    }

    public List<Status> getAllStatus()
    {
        List<Status> statusList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                STATUS_TABLE,
                new String[] {STATUS_KEY_FIELD_ID, STATUS_STATUS},
                null, null, null, null, null, null );

        if(cursor.moveToFirst())
        {
            do {
                Status status = new Status(getSchedule(cursor.getLong(0)),
                        cursor.getInt(1));
                statusList.add(status);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return statusList;
    }

    public void deleteStatus(Status status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STATUS_TABLE, STATUS_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(status.getmId())});

        db.close();
    }

    public void deleteAllStatus()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STATUS_TABLE, null, null);
        db.close();
    }

    public void updateStatus(Status status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(STATUS_KEY_FIELD_ID, status.getmSchedule().getmId());
        values.put(STATUS_STATUS, status.getmStatus());

        db.update(STATUS_TABLE, values, STATUS_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(status.getmSchedule().getmId())});
        db.close();
    }

    public Status getStatus(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                STATUS_TABLE,
                new String[] {STATUS_KEY_FIELD_ID, STATUS_STATUS},
                STATUS_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(id)},
                null, null, null, null );
        if(cursor != null) cursor.moveToFirst();

        Status status = new Status(
                getSchedule(cursor.getLong(0)),
                cursor.getInt(1));
        cursor.close();
        db.close();
        return status;

    }

    //************************* LOGIN TABLE OPERATIONS **************

    public void addLogin(Login login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(LOGIN_EMAIL, login.getmEmail());
        values.put(LOGIN_PASSWORD, login.getmPassowrd());
        values.put(LOGIN_IS_VERIFIED, login.getIsProfessor());
        long id = db.insert(LOGIN_TABLE, null, values);
        login.setmId(id);
        db.close();


    }

    public List<Login> getAllLogin()
    {
        List<Login> loginList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOGIN_TABLE,
                new String[] { LOGIN_EMAIL, LOGIN_PASSWORD, LOGIN_IS_VERIFIED},
                null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            do {
                Login login = new Login(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2));
                loginList.add(login);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return loginList;
    }


    public void deleteLogin(Login login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOGIN_TABLE, LOGIN_EMAIL + " =?",
                new String[] {String.valueOf(login.getmId())});
        db.close();
    }

    public void deleteAllLogin()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOGIN_TABLE, null, null);
        db.close();
    }

    public Login getLogin(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOGIN_TABLE,
                new String[] { LOGIN_EMAIL, LOGIN_PASSWORD, LOGIN_IS_VERIFIED},
                LOGIN_EMAIL + " =?",
                new String[] {String.valueOf(id)},
                null, null, null, null );

        if(cursor != null) cursor.moveToFirst();

        Login login = new Login(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        cursor.close();
        db.close();
        return  login;
    }

    //************************* VERIFICATION TABLE OPERATIONS **************

    public void addVerification(Verification verification)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERIFICATION_EMAIL, verification.getmEmail());
        values.put(VERIFICATION_PIN, verification.getmPin());
        values.put(VERIFICATION_LAST_NAME, verification.getmLastName());
        values.put(VERIFICATION_FIRST_NAME, verification.getmFirstName());
        values.put(VERIFICATION_IS_VERIFIED, verification.ismIsVerified()) ;

        db.insert(VERIFICATION_TABLE, null, values);
        db.close();
    }

    public List<Verification> getAllVerifications()
    {
        List<Verification> verificationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                VERIFICATION_TABLE,
                new String[] {VERIFICATION_KEY_FIELD_ID, VERIFICATION_EMAIL, VERIFICATION_PIN,
                        VERIFICATION_LAST_NAME, VERIFICATION_FIRST_NAME, VERIFICATION_IS_VERIFIED},
                null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do {
                Verification verification = new Verification(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5));
                verificationsList.add(verification);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return verificationsList;
    }

    public void deleteVerification(Verification verification)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VERIFICATION_TABLE, VERIFICATION_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(verification.getmId())});
        db.close();
    }

    public void deleteAllVerifications()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VERIFICATION_TABLE, null, null);
        db.close();
    }

    public void updateVerification(Verification verification)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERIFICATION_EMAIL, verification.getmEmail());
        values.put(VERIFICATION_PIN, verification.getmPin());
        values.put(VERIFICATION_LAST_NAME, verification.getmLastName());
        values.put(VERIFICATION_FIRST_NAME, verification.getmFirstName());
        values.put(VERIFICATION_IS_VERIFIED, verification.ismIsVerified()) ;

        db.update(VERIFICATION_TABLE, values, VERIFICATION_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(verification.getmId())});
        db.close();
    }

    public  Verification getVerification(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                VERIFICATION_TABLE,
                new String[] {VERIFICATION_KEY_FIELD_ID, VERIFICATION_EMAIL, VERIFICATION_PIN,
                        VERIFICATION_LAST_NAME, VERIFICATION_FIRST_NAME, VERIFICATION_IS_VERIFIED},
                VERIFICATION_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(id)},
                null, null, null, null );
        if(cursor != null) cursor.moveToFirst();

        Verification verification = new Verification(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5));

        cursor.close();
        db.close();
        return verification;
    }

    //************************* SAVED INSTRUCTOR TABLE OPERATIONS **************

    public void addSavedInstructor(int instructorCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SAVED_INSTR_CODE, instructorCode);

        db.insert(SAVED_TABLE, null, values);
        db.close();
    }

    public List<SavedInstructor> getAllSavedInstructors()
    {
        List<SavedInstructor> savedInstructorsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SAVED_TABLE,
                new String[] {SAVED_INSTR_CODE},
                null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do {
                SavedInstructor savedInstructor =
                        new SavedInstructor(getInstructor(cursor.getInt(0)));
                savedInstructorsList.add(savedInstructor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return savedInstructorsList;
    }

    public void deleteSavedInstructor(SavedInstructor savedInstructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SAVED_TABLE, SAVED_INSTR_CODE + "=?",
                new String[] {String.valueOf(savedInstructor.getmInstructor().getmId())});
        db.close();
    }

    public void deleteAllSavedInstructors()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SAVED_TABLE, null, null);
        db.close();
    }

    public void updateSavedInstructor(SavedInstructor savedInstructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SAVED_INSTR_CODE, savedInstructor.getmInstructor().getmId());
        db.update(SAVED_TABLE, values, SAVED_INSTR_CODE + " =?",
                new String[]{String.valueOf(savedInstructor.getmInstructor().getmId())});
        db.close();
    }

    public SavedInstructor getSavedInstructor(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SAVED_TABLE,
                new String[] { SAVED_INSTR_CODE},
                SAVED_INSTR_CODE + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null);
        if(cursor != null) cursor.moveToFirst();

        Instructor instructor = getInstructor(cursor.getLong(0));
        SavedInstructor savedInstructor = new SavedInstructor(instructor);
        cursor.close();
        db.close();
        return  savedInstructor;
    }


    // ******************* IMPORT FROM CSV OPERATIONS **********

    public boolean importInstructorsFromCSV(String csvFileName)
    {
        AssetManager am = mContext.getAssets();
        InputStream inStream = null;
        try{
            inStream = am.open(csvFileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try{
            while ((line = buffer.readLine()) != null)
            {
                String[] fields = line.split(",");
                if(fields.length != 6) {
                    Log.d("Instructor Finder", "Skipping bad CSV Row: " +
                            Arrays.toString(fields));
                    continue;
                }
                int instructorCode = Integer.parseInt(fields[0].trim());
                String lastName = fields[1].trim();
                String firstName = fields[2].trim();
                String phone = fields[3].trim();
                String room = fields[4].trim();
                int appointment = Integer.parseInt(fields[5].trim());
                addInstructor(new Instructor(instructorCode, lastName, firstName, phone, room, appointment));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return  true;

    }

    public boolean importScheduleFromCSV(String csvFileName)
    {
        AssetManager am = mContext.getAssets();
        InputStream inStream = null;
        try{
            inStream = am.open(csvFileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try{
            while ((line = buffer.readLine()) != null)
            {
                String[] fields = line.split(",");
                if(fields.length != 5) {
                    Log.d("Schedule Finder", "Skipping bad CSV Row: " +
                            Arrays.toString(fields));
                    continue;
                }
                int instructorCode = Integer.parseInt(fields[0].trim());
                int section = Integer.parseInt(fields[1].trim());
                int day = Integer.parseInt(fields[2].trim());
                String time = fields[3].trim();
                String location = fields[4].trim();
                addSchedule(new Schedule(getInstructor(instructorCode), section, day, time, location));

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return  true;

    }


    public boolean importVerifiedFromCSV(String csvFileName)
    {
        AssetManager am = mContext.getAssets();
        InputStream inStream = null;
        try{
            inStream = am.open(csvFileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try{
            while ((line = buffer.readLine()) != null)
            {
                String[] fields = line.split(",");
                if(fields.length != 5) {
                    Log.d("Schedule Finder", "Skipping bad CSV Row: " +
                            Arrays.toString(fields));
                    continue;
                }
                String email = fields[0].trim();
                int pin = Integer.parseInt(fields[1].trim());
                String last = fields[2].trim();
                String first = fields[3].trim();
                int verified = Integer.parseInt(fields[4].trim());
                addVerification(new Verification(email, pin, last, first, verified));

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return  true;

    }





}
