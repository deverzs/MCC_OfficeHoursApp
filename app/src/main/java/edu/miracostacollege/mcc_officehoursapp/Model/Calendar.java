package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Calendar {

    private int mInstructorCode;        //Unique code for the instructor
    private int mOfficeHourSection;     //Designates which section of the day this office hour is
    private int mOfficeHourDay;         //Designates the day of the office hour
    private String mOfficeHourTime;     //Designates time of office hour
    private String mOfficeHourLocation; //Designates location of office hour


    /**
     * Constructor for Calendar Table
     * @param mInstructorCode       Unique code for the instructor
     * @param mOfficeHourSection    Designates which section of the day this office hour is
     * @param mOfficeHourDay        Designates the day of the office hour
     * @param mOfficeHourTime       Designates time of office hour
     * @param mOfficeHourLocation   Designates location of office hour
     */
    public Calendar(int mInstructorCode, int mOfficeHourSection, int mOfficeHourDay,
                       String mOfficeHourTime, String mOfficeHourLocation) {
        this.mInstructorCode = mInstructorCode;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
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
     * @return
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
        return "Calendar{" +
                "mInstructorCode=" + mInstructorCode +
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
        Calendar calendar = (Calendar) o;
        return getmInstructorCode() == calendar.getmInstructorCode() &&
                getmOfficeHourSection() == calendar.getmOfficeHourSection() &&
                getmOfficeHourDay() == calendar.getmOfficeHourDay() &&
                getmOfficeHourTime().equals(calendar.getmOfficeHourTime()) &&
                getmOfficeHourLocation().equals(calendar.getmOfficeHourLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmInstructorCode(), getmOfficeHourSection(), getmOfficeHourDay(), getmOfficeHourTime(), getmOfficeHourLocation());
    }
}
