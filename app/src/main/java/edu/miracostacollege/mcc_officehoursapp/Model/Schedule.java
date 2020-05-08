package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Schedule {

    private long mId;                   //Database assigned
    private int mInstructorCode;        //Unique code for the instructor
    private int mOfficeHourSection;     //Designates which section of the day this office hour is
    private int mOfficeHourDay;         //Designates the day of the office hour
    private String mOfficeHourTime;     //Designates time of office hour
    private String mOfficeHourLocation; //Designates location of office hour


    /**
     * Constructor for Calendar Table
     * @param id                    Database assigned id
     * @param mInstructorCode       Unique code for the instructor
     * @param mOfficeHourSection    Designates which section of the day this office hour is
     * @param mOfficeHourDay        Designates the day of the office hour
     * @param mOfficeHourTime       Designates time of office hour
     * @param mOfficeHourLocation   Designates location of office hour
     */
    public Schedule(long id, int mInstructorCode, int mOfficeHourSection, int mOfficeHourDay,
                    String mOfficeHourTime, String mOfficeHourLocation) {
        this.mInstructorCode = mInstructorCode;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
        this.mId = id;
    }


    /**
     * Database assigned id
     * @return long id in database
     */
    public long getmId() {
        return mId;
    }

    /**
     * Get the instructor code
     * @return  int: Unique code for the instructor
     */
    public int getmInstructorCode() {
        return mInstructorCode;
    }

    /**
     * Set instructor code
     * @param mInstructorCode  Unique code for the instructor, int
     */
    public void setmInstructorCode(int mInstructorCode) {
        this.mInstructorCode = mInstructorCode;
    }

    /**
     * Get the office hour section number
     * @return in that is the office hour section
     */
    public int getmOfficeHourSection() {
        return mOfficeHourSection;
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

    @Override
    public String toString() {
        return "Schedule{" +
                "mId=" + mId +
                ", mInstructorCode=" + mInstructorCode +
                ", mOfficeHourSection=" + mOfficeHourSection +
                ", mOfficeHourDay=" + mOfficeHourDay +
                ", mOfficeHourTime='" + mOfficeHourTime + '\'' +
                ", mOfficeHourLocation='" + mOfficeHourLocation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getmId() == schedule.getmId() &&
                getmInstructorCode() == schedule.getmInstructorCode() &&
                getmOfficeHourSection() == schedule.getmOfficeHourSection() &&
                getmOfficeHourDay() == schedule.getmOfficeHourDay() &&
                getmOfficeHourTime().equals(schedule.getmOfficeHourTime()) &&
                getmOfficeHourLocation().equals(schedule.getmOfficeHourLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmId(), getmInstructorCode(), getmOfficeHourSection(),
                getmOfficeHourDay(), getmOfficeHourTime(), getmOfficeHourLocation());
    }
}
