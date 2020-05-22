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

/**
 * DBHelper that contains the creation and references to the Database.
 *
 * These include Tables: Instructor, Login, SavedInstructor, Schedule, Status, Verification
 * Each table can be accessed via: add, update, get, getAll and delete
 * Also included are importing from csv files
 */
public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    public static final String DATABASE_NAME = "OfficeHours";
    private static final int DATABASE_VERSION = 1;

    //FIELDS (COLUMN NAMES) FOR THE INSTRUCTOR TABLE
    private static final String INSTRUCTOR_TABLE = "Instructor";
    private static final String INSTRUCTOR_KEY_FIELD_ID = "_id";
    private static final String INSTRUCTOR_FIRST_NAME = "firstName";
    private static final String INSTRUCTOR_LAST_NAME = "lastName";
    private static final String INSTRUCTOR_PHONE = "phone";
    private static final String INSTRUCTOR_OFFICE = "office";
    private static final String INSTRUCTOR_APPOINTMENT = "appointment";


    //FIELDS (COLUMN NAMES) FOR THE SCHEDULE TABLE
    private static final String SCHEDULE_TABLE = "Schedule";
    private static final String SCHEDULE_KEY_FIELD_ID = "_id";
    private static final String SCHEDULE_INSTR_CODE = "code";
    private static final String SCHEDULE_SECTION = "section";
    private static final String SCHEDULE_DAY = "day";
    private static final String SCHEDULE_TIME = "time";
    private static final String SCHEDULE_LOCATION = "location";
    private static final String SCHEDULE_IN_SESSION = "in_session";

    //FIELDS (COLUMN NAMES) FOR THE LOGIN TABLE
    private static final String LOGIN_TABLE = "Login_Table";
    private static final String LOGIN_KEY_FIELD_ID = "_id";
    private static final String LOGIN_EMAIL = "email_login";
    private static final String LOGIN_PASSWORD = "password";
    private static final String LOGIN_IS_VERIFIED = "verifiedProfessor";

    //FIELDS (COLUMN NAMES) FOR THE VERIFICATION TABLE
    private static final String VERIFICATION_TABLE = "Verification";
    private static final String VERIFICATION_KEY_FIELD_ID = "_id";
    private static final String VERIFICATION_EMAIL = "email_verify";
    private static final String VERIFICATION_PIN = "pin";
    private static final String VERIFICATION_LAST_NAME = "lastName_verify";
    private static final String VERIFICATION_FIRST_NAME = "firstName_verify";
    private static final String VERIFICATION_IS_VERIFIED = "isVerified";

    //FIELDS (COLUMN NAMES) FOR THE SAVED INSTRUCTOR  TABLE
    private static final String SAVED_TABLE = "SavedInstructor";
    private static final String SAVED_INSTR_CODE = "code_saved";


    /**
     * To establish the database
     * @param context where to create
     */
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
                + SCHEDULE_LOCATION + " TEXT, "
                + SCHEDULE_IN_SESSION + " INTEGER" + ")";
        db.execSQL(scheduleTable);

        String loginTable = "CREATE TABLE IF NOT EXISTS " + LOGIN_TABLE + " ("
                + LOGIN_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + LOGIN_EMAIL + " TEXT, "
                + LOGIN_PASSWORD + " TEXT, "
                + LOGIN_IS_VERIFIED + " INTEGER" + ")";
        db.execSQL(loginTable);


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
                + "FOREIGN KEY(" + VERIFICATION_KEY_FIELD_ID + ")REFERENCES "
                + LOGIN_TABLE + "(" + LOGIN_KEY_FIELD_ID + "))";
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
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VERIFICATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SAVED_TABLE);
        onCreate(db);

    }

    //************************* INSTRUCTOR TABLE OPERATIONS **************

    /**
     * Adding an instructor to the databse
     * @param instructor Instructor
     */
    public void addInstructor(Instructor instructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(INSTRUCTOR_LAST_NAME, instructor.getmLastName());
        values.put(INSTRUCTOR_FIRST_NAME, instructor.getmFirstName());
        values.put(INSTRUCTOR_PHONE, instructor.getmPhone());
        values.put(INSTRUCTOR_OFFICE, instructor.getmOfficeRoomNumber());
        values.put(INSTRUCTOR_APPOINTMENT, instructor.byAppointment());

        db.insert(INSTRUCTOR_TABLE, null, values);
        db.close();

    }


    /**
     * Get all instructors from database
     * @return  a list of all Instructors
     */
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

    /**
     * Delete an instructor from the database
     * @param instructor  the Instructor to delete
     * @return row of the instructor
     */
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

    /**
     * Delete all instructors from database
     */
    public void deleteAllInstructors()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(INSTRUCTOR_TABLE, null, null);
        db.close();

    }

    /**
     * Update an Instructor
     * @param instructor Instructor to update
     */
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

    /**
     * Get an Instructor
     * @param id the long id of the isntructor
     * @return an Instructor
     */
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

    /**
     * Add a Schedule to the database
     * @param schedule A Schedule to add
     */
    public void addSchedule(Schedule schedule)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHEDULE_INSTR_CODE, schedule.getmInstructor().getmId());
        values.put(SCHEDULE_SECTION, schedule.getmOfficeHourSection());
        values.put(SCHEDULE_DAY, schedule.getmOfficeHourDay());
        values.put(SCHEDULE_TIME, schedule.getmOfficeHourTime());
        values.put(SCHEDULE_LOCATION, schedule.getmOfficeHourLocation());
        values.put(SCHEDULE_IN_SESSION, schedule.getInSession());

        long id = db.insert(SCHEDULE_TABLE, null, values);
        schedule.setmId(id);
        db.close();
    }

    /**
     * Get all schedules from the database
     * @return  a list of schedules
     */
    public List<Schedule> getAllSchedules()
    {
        List<Schedule> scheduleList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SCHEDULE_TABLE,
                new String[] {SCHEDULE_KEY_FIELD_ID, SCHEDULE_INSTR_CODE, SCHEDULE_SECTION,
                        SCHEDULE_DAY, SCHEDULE_TIME, SCHEDULE_LOCATION, SCHEDULE_IN_SESSION},
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Schedule schedule =
                        new Schedule(cursor.getLong(0),
                                getInstructor(cursor.getInt(1)),
                                cursor.getInt(2),
                                cursor.getInt(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getInt(6));
                scheduleList.add(schedule);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scheduleList;
    }

    /**
     * Delete a Schedule from the database
     * @param schedule Schedule to delete
     * @return the row of the schedule
     */
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

    /**
     * Delete all the schedules from the database
     */
    public void deleteAllSchedules()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SCHEDULE_TABLE, null, null);
        db.close();
    }

    /**
     * Update  a schedule in the database
     * @param schedule the Schedule to update
     */
    public void updateSchedule(Schedule schedule)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHEDULE_INSTR_CODE, schedule.getmInstructor().getmId());
        values.put(SCHEDULE_SECTION, schedule.getmOfficeHourSection());
        values.put(SCHEDULE_DAY, schedule.getmOfficeHourDay());
        values.put(SCHEDULE_TIME, schedule.getmOfficeHourTime());
        values.put(SCHEDULE_LOCATION, schedule.getmOfficeHourLocation());
        values.put(SCHEDULE_IN_SESSION, schedule.getInSession());

        db.update(SCHEDULE_TABLE, values, SCHEDULE_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(schedule.getmId())});

        db.close();

    }

    /**
     * Get a schedule
     * @param id the id of the schedule
     * @return the Schedule
     */
    public Schedule getSchedule(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SCHEDULE_TABLE,
                new String[] {SCHEDULE_KEY_FIELD_ID, SCHEDULE_INSTR_CODE, SCHEDULE_SECTION,
                        SCHEDULE_DAY, SCHEDULE_TIME, SCHEDULE_LOCATION, SCHEDULE_IN_SESSION},
                SCHEDULE_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(id)},
                null, null, null, null );

        if(cursor != null)  cursor.moveToFirst();

        Schedule schedule = new Schedule(cursor.getLong(0),
                getInstructor(cursor.getInt(1)),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6));
        cursor.close();
        db.close();
        return schedule;
    }



    //************************* LOGIN TABLE OPERATIONS **************

    /**
     * Add a Login to the database
     * @param login the Login to add
     */
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

    /**
     * Get all the logins from the database
     * @return the list with the logins
     */
    public List<Login> getAllLogin()
    {
        List<Login> loginList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOGIN_TABLE,
                new String[] { LOGIN_KEY_FIELD_ID, LOGIN_EMAIL, LOGIN_PASSWORD, LOGIN_IS_VERIFIED},
                null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            do {
                Login login = new Login(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3));
                loginList.add(login);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return loginList;
    }


    /**
     * Delete the Login from the database
     * @param login the Login to delete
     */
    public void deleteLogin(Login login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOGIN_TABLE, LOGIN_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(login.getmId())});
        db.close();
    }

    /**
     * Delete all the Logins from the table
     */
    public void deleteAllLogin()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOGIN_TABLE, null, null);
        db.close();
    }

    /**
     * Get a login from the table
     * @param id the id of the login
     * @return Login
     */
    public Login getLogin(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOGIN_TABLE,
                new String[] { LOGIN_KEY_FIELD_ID, LOGIN_EMAIL, LOGIN_PASSWORD, LOGIN_IS_VERIFIED},
                LOGIN_KEY_FIELD_ID + " =?",
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

    /**
     * Add a verification to the table
     * @param verification the Verification to add
     */
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

    /**
     * The list of the verifications
     * @return a list
     */
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

    /**
     * Delete a verification from the table
     * @param verification verification to delete
     */
    public void deleteVerification(Verification verification)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VERIFICATION_TABLE, VERIFICATION_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(verification.getmId())});
        db.close();
    }

    /**
     * Delete all verifications
     */
    public void deleteAllVerifications()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VERIFICATION_TABLE, null, null);
        db.close();
    }

    /**
     * Update a verification in the table
     * @param verification a Verification
     */
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

    /**
     * Get a verification
     * @param id an id to verification
     * @return the Verification
     */
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

    /**
     * Add  a SavedInstructor
     * @param instructorCode the id of the instructor
     */
    public void addSavedInstructor(long instructorCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SAVED_INSTR_CODE, instructorCode);

        db.insert(SAVED_TABLE, null, values);

        db.close();
    }

    /**
     * Get all the Saved Instructors
     * @return a list of Saved Instructors
     */
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

    /**
     * Delete a SavedInstructor
     * @param savedInstructor  the SavedInstructor to delete
     */
    public void deleteSavedInstructor(SavedInstructor savedInstructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SAVED_TABLE, SAVED_INSTR_CODE + "=?",
                new String[] {String.valueOf(savedInstructor.getmInstructor().getmId())});
        db.close();
    }

    /**
     * Delete all Saved Instructors from the database
     */
    public void deleteAllSavedInstructors()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SAVED_TABLE, null, null);
        db.close();
    }

    /**
     * Update a Saved Instructor
     * @param savedInstructor the Saved Instructor to update
     */
    public void updateSavedInstructor(SavedInstructor savedInstructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SAVED_INSTR_CODE, savedInstructor.getmInstructor().getmId());
        db.update(SAVED_TABLE, values, SAVED_INSTR_CODE + " =?",
                new String[]{String.valueOf(savedInstructor.getmInstructor().getmId())});
        db.close();
    }

    /**
     * Get a Saved Instructor
     * @param id the id of the Saved Instructor
     * @return Saved Instructor
     */
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

    /**
     * Read from a csv file to create the database for Instructors
     * @param csvFileName file name of csv
     * @return whether it was successful
     */
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

    /**
     * Read from a csv file to create the database for Schedules
     * @param csvFileName file name of csv
     * @return whether it was successful
     */
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
                if(fields.length != 6) {
                    Log.d("Schedule Finder", "Skipping bad CSV Row: " +
                            Arrays.toString(fields));
                    continue;
                }
                int instructorCode = Integer.parseInt(fields[0].trim());
                int section = Integer.parseInt(fields[1].trim());
                int day = Integer.parseInt(fields[2].trim());
                String time = fields[3].trim();
                String location = fields[4].trim();
                int sessionInt = Integer.parseInt(fields[5].trim());
                addSchedule(new Schedule( getInstructor(instructorCode),section,
                        day, time, location, sessionInt));

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return  true;

    }


    /**
     * Read from a csv file to create the database for Verifications
     * @param csvFileName file name of csv
     * @return whether it was successful
     */
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