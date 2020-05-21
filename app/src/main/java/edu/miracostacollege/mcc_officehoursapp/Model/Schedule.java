package edu.miracostacollege.mcc_officehoursapp.Model;

public class Schedule {

    private  long mId;                  //Database assigned
    private Instructor mInstructor;
    private int mOfficeHourSection;     //Designates which section of the day this office hour is
    private int mOfficeHourDay;         //Designates the day of the office hour
    private String mOfficeHourTime;     //Designates time of office hour
    private String mOfficeHourLocation; //Designates location of office hour
    private int inSession;

    public Schedule(Instructor mInstructor, int mOfficeHourSection, int mOfficeHourDay,
                    String mOfficeHourTime, String mOfficeHourLocation, int inSession) {
        this.mInstructor = mInstructor;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
        this.inSession = inSession;
    }

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

    public int getInSession() {
        return inSession;
    }

    public void setInSession(int inSession) {
        this.inSession = inSession;
    }

    public long getmId() {
        return mId;
    }

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

    public Instructor getmInstructor() {
        return mInstructor;
    }

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
