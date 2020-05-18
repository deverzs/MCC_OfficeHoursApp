package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Verification {

    private long mId;                   //Database assigned
    private String mEmail;              //Email of user
    private int mPin;                   //pin of user
    private String mFirstName;
    private String mLastName;
    private boolean mIsVerified;


    public Verification(long mId, String mEmail, int mPin, String mFirstName, String mLastName, int mIsVerified) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mPin = mPin;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        if(mIsVerified == 0) this.mIsVerified = false;
        else this.mIsVerified = true;
    }

    public int ismIsVerified() {
        if(mIsVerified) return 1;
        else return 0;
    }

    public void setmIsVerified(boolean mIsVerified) {
        this.mIsVerified = mIsVerified;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPin() {
        return mPin;
    }

    public void setmPin(int mPin) {
        this.mPin = mPin;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

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
