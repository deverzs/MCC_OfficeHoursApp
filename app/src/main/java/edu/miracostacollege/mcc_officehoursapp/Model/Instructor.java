package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;


public class Instructor {

    private int mInstructorCode;        //Unique code for the instructor
    private String mFullName;           //Full Name of instructor
    private String mPhone;              //Phone number, including extension
    private String mOfficeRoomNumber;   //Room number of instructor's office
    private boolean mAppointment;       //True if instructor accepts appointments

    /**
     * Default Construstor - Full
     * @param mInstructorCode       Unique code for the instructor
     * @param mFullName             Full Name of instructor
     * @param mPhone                Phone number, including extension
     * @param mOfficeRoomNumber     Room number of instructor's office
     * @param mAppointment          True if instructor accepts appointments
     */
    public Instructor(int mInstructorCode, String mFullName, String mPhone,
                       String mOfficeRoomNumber, boolean mAppointment) {
        this.mInstructorCode = mInstructorCode;
        this.mFullName = mFullName;
        this.mPhone = mPhone;
        this.mOfficeRoomNumber = mOfficeRoomNumber;
        this.mAppointment = mAppointment;

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
     * Get full name
     * @return  string: Full Name of instructor
     */
    public String getmFullName() {
        return mFullName;
    }

    /**
     * Set full name
     * @param mFullName  Full Name of instructor, String
     */
    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    /**
     * Get phone number
     * @return  string: Phone number, including extension
     */
    public String getmPhone() {
        return mPhone;
    }

    /**
     * Set phone number
     * @param mPhone   Phone number, including extension
     */
    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    /**
     * Get Office room number of instructor
     * @return  string: Room number of instructor's office
     */
    public String getmOfficeRoomNumber() {
        return mOfficeRoomNumber;
    }

    /**
     * Set Office room number of instructor
     * @param mOfficeRoomNumber  string: Room number of instructor's office
     */
    public void setmOfficeRoomNumber(String mOfficeRoomNumber) {
        this.mOfficeRoomNumber = mOfficeRoomNumber;
    }

    /**
     * Get whether available  by appointment
     * @return  0=False, not available by appointment, 1=True available by appointement
     */
    public boolean ismAppointment() {
        return mAppointment;
    }

    /**
     * Set whether available  by appointment
     * @param mAppointment  0=False, not available by appointment, 1=True available by appointement
     */
    public void setmAppointment(boolean mAppointment) {
        this.mAppointment = mAppointment;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "mInstructorCode=" + mInstructorCode +
                ", mFullName='" + mFullName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mOfficeRoomNumber='" + mOfficeRoomNumber + '\'' +
                ", mAppointment=" + mAppointment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return getmInstructorCode() == that.getmInstructorCode() &&
                ismAppointment() == that.ismAppointment() &&
                getmFullName().equals(that.getmFullName()) &&
                getmPhone().equals(that.getmPhone()) &&
                getmOfficeRoomNumber().equals(that.getmOfficeRoomNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmInstructorCode(), getmFullName(), getmPhone(), getmOfficeRoomNumber(), ismAppointment());
    }
}
