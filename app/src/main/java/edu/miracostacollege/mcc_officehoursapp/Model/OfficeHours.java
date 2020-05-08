package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class OfficeHours {
    private int mInstructorCode;        //Unique code for the instructor
    private String mFullName;           //Full Name of instructor
    private String mPhone;              //Phone number, including extension
    private String mOfficeRoomNumber;   //Room number of instructor's office
    private boolean mAppointment;       //True if instructor accepts appointments

    private int mOfficeHourSection;     //Designates which section of the day this office hour is
    private int mOfficeHourDay;         //Designates the day of the office hour
    private String mOfficeHourTime;     //Designates time of office hour
    private String mOfficeHourLocation; //Designates location of office hour
    private boolean mOfficeHourStatus;  //True if office hour is being conducted

    /**
     * Full Constructor
     * @param mInstructorCode       Unique code for the instructor
     * @param mFullName             Full Name of instructor
     * @param mPhone                Phone number, including extension
     * @param mOfficeRoomNumber     Room number of instructor's office
     * @param mAppointment          True if instructor accepts appointments
     * @param mOfficeHourSection    Designates which section of the day this office hour is
     * @param mOfficeHourDay        Designates the day of the office hour
     * @param mOfficeHourTime       Designates time of office hour
     * @param mOfficeHourLocation   Designates location of office hour
     * @param mOfficeHourStatus     True if office hour is being conducted
     */
    public OfficeHours(int mInstructorCode, String mFullName, String mPhone,
                       String mOfficeRoomNumber, boolean mAppointment, int mOfficeHourSection,
                       int mOfficeHourDay, String mOfficeHourTime, String mOfficeHourLocation,
                       boolean mOfficeHourStatus) {
        this.mInstructorCode = mInstructorCode;
        this.mFullName = mFullName;
        this.mPhone = mPhone;
        this.mOfficeRoomNumber = mOfficeRoomNumber;
        this.mAppointment = mAppointment;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
        this.mOfficeHourStatus = mOfficeHourStatus;
    }

    /**
     * Constructor for Instructor Table
     * @param mInstructorCode       Unique code for the instructor
     * @param mFullName             Full Name of instructor
     * @param mPhone                Phone number, including extension
     * @param mOfficeRoomNumber     Room number of instructor's office
     * @param mAppointment          True if instructor accepts appointments
     */
    public OfficeHours(int mInstructorCode, String mFullName, String mPhone,
                       String mOfficeRoomNumber, boolean mAppointment) {
        this.mInstructorCode = mInstructorCode;
        this.mFullName = mFullName;
        this.mPhone = mPhone;
        this.mOfficeRoomNumber = mOfficeRoomNumber;
        this.mAppointment = mAppointment;
    }

    /**
     * Constructor for Calendar Table
     * @param mInstructorCode       Unique code for the instructor
     * @param mOfficeHourSection    Designates which section of the day this office hour is
     * @param mOfficeHourDay        Designates the day of the office hour
     * @param mOfficeHourTime       Designates time of office hour
     * @param mOfficeHourLocation   Designates location of office hour
     */
    public OfficeHours(int mInstructorCode, int mOfficeHourSection, int mOfficeHourDay,
                       String mOfficeHourTime, String mOfficeHourLocation) {
        this.mInstructorCode = mInstructorCode;
        this.mOfficeHourSection = mOfficeHourSection;
        this.mOfficeHourDay = mOfficeHourDay;
        this.mOfficeHourTime = mOfficeHourTime;
        this.mOfficeHourLocation = mOfficeHourLocation;
    }

    /**
     * Constructor for Status Table
     * @param mInstructorCode       Unique code for the instructor
     * @param mOfficeHourSection    Designates which section of the day this office hour is
     * @param mOfficeHourDay        Designates the day of the office hour
     * @param mOfficeHourStatus     True if office hour is being conducted
     */
    public OfficeHours(int mInstructorCode, int mOfficeHourSection,
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
        return "OfficeHours{" +
                "mInstructorCode=" + mInstructorCode +
                ", mFullName='" + mFullName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mOfficeRoomNumber='" + mOfficeRoomNumber + '\'' +
                ", mAppointment=" + mAppointment +
                ", mOfficeHourSection=" + mOfficeHourSection +
                ", mOfficeHourDay=" + mOfficeHourDay +
                ", mOfficeHourTime='" + mOfficeHourTime + '\'' +
                ", mOfficeHourLocation='" + mOfficeHourLocation + '\'' +
                ", mOfficeHourStatus=" + mOfficeHourStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeHours that = (OfficeHours) o;
        return getmInstructorCode() == that.getmInstructorCode() &&
                ismAppointment() == that.ismAppointment() &&
                getmOfficeHourSection() == that.getmOfficeHourSection() &&
                getmOfficeHourDay() == that.getmOfficeHourDay() &&
                ismOfficeHourStatus() == that.ismOfficeHourStatus() &&
                getmFullName().equals(that.getmFullName()) &&
                getmPhone().equals(that.getmPhone()) &&
                getmOfficeRoomNumber().equals(that.getmOfficeRoomNumber()) &&
                getmOfficeHourTime().equals(that.getmOfficeHourTime()) &&
                getmOfficeHourLocation().equals(that.getmOfficeHourLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmInstructorCode(), getmFullName(), getmPhone(),
                getmOfficeRoomNumber(), ismAppointment(), getmOfficeHourSection(),
                getmOfficeHourDay(), getmOfficeHourTime(), getmOfficeHourLocation(),
                ismOfficeHourStatus());
    }
}
