package edu.miracostacollege.mcc_officehoursapp.Model;

/**
 * Schedule that holds an instructor and their office hours
 */
public class Schedule {

    private  long mId;                  //Database assigned
    private Instructor mInstructor;     //Instructor for office hours
    private int mOfficeHourSection;     //Designates which section of the day this office hour is
    private int mOfficeHourDay;         //Designates the day of the office hour
    private String mOfficeHourTime;     //Designates time of office hour
    private String mOfficeHourLocation; //Designates location of office hour
    private int inSession;

    /**
     * partial Constructor
     * @param mInstructor   Instructor
     * @param mOfficeHourSection  section of their office hours
     * @param mOfficeHourDay  day for office hours
     * @param mOfficeHourTime time of office hours
     * @param mOfficeHourLocation location of office hours
     * @param inSession if the office hours is being held
     */
    public Schedule(Instructor mInstructor, int mOfficeHourSection, int mOfficeHourDay,
                    String mOfficeHourTime, String mOfficeHourLocation, int inSession) {
        this.mInstructor = mInstructor;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
        this.inSession = inSession;
    }

    /**
     * Full constructor
     * @param mId  the id of the schedule
     * @param mInstructor   Instructor
     * @param mOfficeHourSection  section of their office hours
     * @param mOfficeHourDay  day for office hours
     * @param mOfficeHourTime time of office hours
     * @param mOfficeHourLocation location of office hours
     * @param inSession if the office hours is being held
     */

    public Schedule(long mId, Instructor mInstructor, int mOfficeHourSection, int mOfficeHourDay,
                    String mOfficeHourTime, String mOfficeHourLocation, int inSession) {
        this.mId = mId;
        this.mInstructor = mInstructor;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
        this.inSession  = inSession;
    }

    /**
     * Get whether the office hour is being held
     * @return 1 if being held, 0 if not
     */
    public int getInSession() {
        return inSession;
    }

    /**
     * Set whether the office hour is being held
     * @param inSession 1 if being held, 0 if not
     */
    public void setInSession(int inSession) {
        this.inSession = inSession;
    }

    /**
     * Get the id set by the database
     * @return id from database
     */
    public long getmId() {
        return mId;
    }

    /**
     * Set the id from the database
     * @param mId  id
     */
    public void setmId(long mId) {
        this.mId = mId;
    }

    /**
     * Get the office hour section number
     * @return in that is the office hour section
     */
    public int getmOfficeHourSection() {
        return mOfficeHourSection;
    }

    /**
     * Get the Instructor
     * @return  instructor
     */
    public Instructor getmInstructor() {
        return mInstructor;
    }

    /**
     * Set the instructor
     * @param mInstructor  instructor
     */
    public void setmInstructor(Instructor mInstructor) {
        this.mInstructor = mInstructor;
    }

    /**
     * Set the office hour section number
     * @param mOfficeHourSection  int: Designates which section of the day this office hour is
     */
    public void setmOfficeHourSection(int mOfficeHourSection) {
        this.mOfficeHourSection = mOfficeHourSection;
    }

    /**
     * Get the office hour day number (0=Online, 1=Mon, 2=Tues, 3=Wed, 4=Thur, 5=Fri)
     * @return  int:Designates which section of the day this office hour is
     */
    public int getmOfficeHourDay() {
        return mOfficeHourDay;
    }

    /**
     * Set the office hour day number (0=Online, 1=Mon, 2=Tues, 3=Wed, 4=Thur, 5=Fri)
     * @param mOfficeHourDay  int representing the day of the week
     */
    public void setmOfficeHourDay(int mOfficeHourDay) {
        this.mOfficeHourDay = mOfficeHourDay;
    }

    /**
     * Get the office hour time
     * @return  string of office hour time
     */
    public String getmOfficeHourTime() {
        return mOfficeHourTime;
    }

    /**
     * Set the office hour time
     * @param mOfficeHourTime  string of office hour time
     */
    public void setmOfficeHourTime(String mOfficeHourTime) {
        this.mOfficeHourTime = mOfficeHourTime;
    }

    /**
     * Get the office hour location
     * @return  string where office hour being held
     */
    public String getmOfficeHourLocation() {
        return mOfficeHourLocation;
    }

    /**
     * Set the office hour location
     * @param mOfficeHourLocation  string where office hour being held
     */
    public void setmOfficeHourLocation(String mOfficeHourLocation) {
        this.mOfficeHourLocation = mOfficeHourLocation;
    }

}
