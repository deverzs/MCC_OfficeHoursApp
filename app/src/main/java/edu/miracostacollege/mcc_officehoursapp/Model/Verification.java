package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

/**
 * Verification of Instructor as a Professor at MiraCosta
 */
public class Verification {

    private long mId;                   //Database assigned
    private String mEmail;              //Email of user
    private int mPin;                   //pin of user
    private String mFirstName;          //First name
    private String mLastName;           //Last name
    private boolean mIsVerified;        // has been verified


    /**
     * Full Constructor
     * @param mId  Database assigned
     * @param mEmail Email of user
     * @param mPin pin of user
     * @param mLastName first name
     * @param mFirstName last name
     * @param mIsVerified has been verified
     */
    public Verification(long mId, String mEmail, int mPin, String mLastName, String mFirstName,
                        int mIsVerified) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mPin = mPin;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        if(mIsVerified == 0) this.mIsVerified = false;
        else this.mIsVerified = true;
    }

    /**
     * Partial constructor, without id
     * @param mEmail Email of user
     * @param mPin pin of user
     * @param mLastName first name
     * @param mFirstName last name
     * @param mIsVerified has been verified
     */
    public Verification(String mEmail, int mPin, String mLastName, String mFirstName, int mIsVerified) {
        this.mEmail = mEmail;
        this.mPin = mPin;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        if(mIsVerified == 0) this.mIsVerified = false;
        else this.mIsVerified = true;
    }

    /**
     * Check if the Instructor is verified
     * @return  1 if true and verified, 0 if not
     */
    public int ismIsVerified() {
        if(mIsVerified) return 1;
        else return 0;
    }

    /**
     * Check if the Instructor is verified
     * @return Professor or NOT Professor
     */
    public String getIsVerified(){
        if (mIsVerified==true) return "Professor";
        else return "NOT professpr";
    }
    public void setmIsVerified(boolean mIsVerified) {
        this.mIsVerified = mIsVerified;
    }

    /**
     * Return the id
     * @return id
     */
    public long getmId() {
        return mId;
    }

    /**
     * Set the id
     * @param mId  id
     */
    public void setmId(long mId) {
        this.mId = mId;
    }

    /**
     * Get the email
     * @return email
     */
    public String getmEmail() {
        return mEmail;
    }

    /**
     * Set the email
     * @param mEmail email
     */
    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    /**
     * Get the PIN
     * @return pin
     */
    public int getmPin() {
        return mPin;
    }

    /**
     * Set the pin
     * @param mPin  pin
     */
    public void setmPin(int mPin) {
        this.mPin = mPin;
    }

    /**
     * Get first name
     * @return first name
     */
    public String getmFirstName() {
        return mFirstName;
    }

    /**
     * Set first name
     * @param mFirstName first name
     */
    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    /**
     * Get last name
     * @return lat name
     */
    public String getmLastName() {
        return mLastName;
    }

    /**
     * Set last name
     * @param mLastName last name
     */
    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "mId=" + mId +
                ", mEmail='" + mEmail + '\'' +
                ", mPin=" + mPin +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Verification that = (Verification) o;
        return getmId() == that.getmId() &&
                getmPin() == that.getmPin() &&
                getmEmail().equals(that.getmEmail()) &&
                getmFirstName().equals(that.getmFirstName()) &&
                getmLastName().equals(that.getmLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmId(), getmEmail(), getmPin(), getmFirstName(), getmLastName());
    }
}
