package edu.miracostacollege.mcc_officehoursapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    //************************* INSTRUCTOR TABLE OPERATIONS **************

    public void addInstructor(Instructor instructor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(INSTRUCTOR_INSTR_CODE, instructor.getmId());
        values.put(INSTRUCTOR_LAST_NAME, instructor.getmLastName());
        values.put(INSTRUCTOR_FIRST_NAME, instructor.getmFirstName());
        values.put(INSTRUCTOR_PHONE, instructor.getmPhone());
        values.put(INSTRUCTOR_OFFICE, instructor.getmOfficeRoomNumber());
        values.put(INSTRUCTOR_APPOINTMENT, instructor.byAppointment());

        long id = db.insert(INSTRUCTOR_TABLE, null, values);
        instructor.setmId(id);
        db.close();

    }


    public List<Instructor> getAllInstructors()
    {
        List<Instructor> instructorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                INSTRUCTOR_TABLE,
                new String[] {INSTRUCTOR_KEY_FIELD_ID, INSTRUCTOR_INSTR_CODE, INSTRUCTOR_LAST_NAME,
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
                        new Instructor(cursor.getLong(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getInt(6));
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

        values.put(INSTRUCTOR_INSTR_CODE, instructor.getmId());
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
                new String[] {INSTRUCTOR_KEY_FIELD_ID, INSTRUCTOR_INSTR_CODE, INSTRUCTOR_LAST_NAME,
                        INSTRUCTOR_FIRST_NAME, INSTRUCTOR_PHONE, INSTRUCTOR_OFFICE,
                        INSTRUCTOR_APPOINTMENT},
                INSTRUCTOR_KEY_FIELD_ID + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null);

        if(cursor != null) cursor.moveToFirst();

        Instructor instructor = new Instructor (cursor.getLong(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6));
        cursor.close();
        db.close();
        return instructor;

    }

    //************************* SCHEDULE TABLE OPERATIONS **************

    public void addSchedule(Schedule schedule)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHEDULE_KEY_FIELD_ID, schedule.getmId());
        values.put(SCHEDULE_INSTR_CODE, schedule.getmInstructorCode());
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
                                cursor.getInt(1),
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
                new String[] {String.valueOf(schedule.getmId())});
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

        values.put(SCHEDULE_KEY_FIELD_ID, schedule.getmId());
        values.put(SCHEDULE_INSTR_CODE, schedule.getmInstructorCode());
        values.put(SCHEDULE_SECTION, schedule.getmOfficeHourSection());
        values.put(SCHEDULE_DAY, schedule.getmOfficeHourDay());
        values.put(SCHEDULE_TIME, schedule.getmOfficeHourTime());
        values.put(SCHEDULE_LOCATION, schedule.getmOfficeHourLocation());

        db.update(SCHEDULE_TABLE, values, SCHEDULE_KEY_FIELD_ID + " =?",
                new String[] {String.valueOf(schedule.getmId())});

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
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5));
        cursor.close();
        db.close();
        return schedule;
    }



}
