package edu.miracostacollege.mcc_officehoursapp.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;
import java.util.Objects;

/**
 * Instructor holds a unique object that represents a Professor
 */
public class Instructor implements Parcelable
{

    private long mId;                  //Unique code for the instructor
    private String mFullName;           //Full Name of instructor
    private String mFirstName;          //First Name of instructor
    private String mLastName;           //Last Name of instructor
    private String mPhone;              //Phone number, including extension
    private String mOfficeRoomNumber;   //Room number of instructor's office
    private boolean mAppointment;       //True if instructor accepts appointments

    /**
     * Default Construstor - Full
     * @param id                    Unique code for the instructor
     * @param mFirstName             First Name of instructor
     * @param mLastName             Last Name of instructor
     * @param mPhone                Phone number, including extension
     * @param mOfficeRoomNumber     Room number of instructor's office
     * @param mAppointment          True if instructor accepts appointments
     */
    public Instructor(long id, String mFirstName, String mLastName, String mPhone,
                      String mOfficeRoomNumber, int mAppointment) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mPhone = mPhone;
        this.mOfficeRoomNumber = mOfficeRoomNumber;
        if(mAppointment == 0) this.mAppointment = false;
        else this.mAppointment = true;
        this.mId = id;
        this.mFullName = mFirstName + " " + mLastName;

    }


    /**
     * Get the first name
     * @return first name
     */
    public String getmFirstName() {
        return mFirstName;
    }

    /**
     * Get last name
     * @return last name
     */
    public String getmLastName() {
        return mLastName;
    }

    /**
     * set first name
     * @param mFirstName  first name
     */
    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    /**
     * Sets the id given by database
     * @param mId
     */
    public void setmId(long mId) {
        this.mId = mId;
    }

    /**
     * Set last name
     * @param mLastName set last name
     */
    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    /**
     * ID assigned by database
     * @return long that is the database assigned id
     */
    public long getmId() {
        return mId;
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
    //
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
     * Return integer value of appointment bool
     * @return 1 if true 0 else
     */
    public int byAppointment()
    {
        if(ismAppointment()) return 1;
        else return 0;
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
                "mId=" + mId +
                ", mInstructorCode="  +
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
        return mId == that.mId &&
                ismAppointment() == that.ismAppointment() &&
                getmFullName().equals(that.getmFullName()) &&
                getmPhone().equals(that.getmPhone()) &&
                getmOfficeRoomNumber().equals(that.getmOfficeRoomNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, getmFullName(), getmPhone(),
                getmOfficeRoomNumber(), ismAppointment());
    }

    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * Writing the object to a parcel
     * @param dest where to write to
     * @param flags  not sure
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mFullName);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mPhone);
        dest.writeString(mOfficeRoomNumber);
        if(mAppointment) dest.writeInt(0);
        else dest.writeInt(1);
    }

    /**
     * Parcelable Creator
     */
  public static final Parcelable.Creator<Instructor> CREATOR = new Creator<Instructor>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Instructor createFromParcel(Parcel source) {
            return new Instructor(source);
        }

        @Override
        public Instructor[] newArray(int size) {
            return new Instructor[size];
        }
    };

    /**
     * Creating the Instructor as a parcel object
     * @param parcel the object that is the Instructor
     */
    private Instructor(Parcel parcel)
    {
        mId = parcel.readLong();
        mFullName = parcel.readString();
        mFirstName = parcel.readString();
        mLastName = parcel.readString();
        mPhone = parcel.readString();
        mOfficeRoomNumber = parcel.readString();
        if(parcel.readInt() == 0) mAppointment = true;
        else mAppointment = false;
    }

}
