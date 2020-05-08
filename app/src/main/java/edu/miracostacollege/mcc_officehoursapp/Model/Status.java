package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Status {

    private int mInstructorCode;        //Unique code for the instructor
    private int mOfficeHourSection;     //Designates which section of the day this office hour is
    private int mOfficeHourDay;         //Designates the day of the office hour
    private boolean mOfficeHourStatus;  //True if office hour is being conducted


    /**
     * Constructor for Status Table
     * @param mInstructorCode       Unique code for the instructor
     * @param mOfficeHourSection    Designates which section of the day this office hour is
     * @param mOfficeHourDay        Designates the day of the office hour
     * @param mOfficeHourStatus     True if office hour is being conducted
     */
    public Status(int mInstructorCode, int mOfficeHourSection,
                       int mOfficeHourDay, boolean mOfficeHourStatus) {
        this.mInstructorCode = mInstructorCode;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourStatus = mOfficeHourStatus;
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
     * Get whether office hour is being held
     * @return  0=False, cancelled, 1=True, session is being held
     */
    public boolean ismOfficeHourStatus() {
        return mOfficeHourStatus;
    }

    /**
     * Set whether office hour is being held
     * @param mOfficeHourStatus  0=False, cancelled, 1=True, session is being held
     */
    public void setmOfficeHourStatus(boolean mOfficeHourStatus) {
        this.mOfficeHourStatus = mOfficeHourStatus;
    }

    @Override
    public String toString() {
        return "Status{" +
                "mInstructorCode=" + mInstructorCode +
                ", mOfficeHourSection=" + mOfficeHourSection +
                ", mOfficeHourDay=" + mOfficeHourDay +
                ", mOfficeHourStatus=" + mOfficeHourStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return getmInstructorCode() == status.getmInstructorCode() &&
                getmOfficeHourSection() == status.getmOfficeHourSection() &&
                getmOfficeHourDay() == status.getmOfficeHourDay() &&
                ismOfficeHourStatus() == status.ismOfficeHourStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmInstructorCode(), getmOfficeHourSection(), getmOfficeHourDay(), ismOfficeHourStatus());
    }
}
