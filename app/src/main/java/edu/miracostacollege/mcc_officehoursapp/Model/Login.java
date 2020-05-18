package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Login {

    private long mId;                   //Database assigned
    private String mEmail;              //Email of user
    private String mPassowrd;           //password of user


    public Login(long mId, String mEmail, String mPassowrd) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mPassowrd = mPassowrd;
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

    public String getmPassowrd() {
        return mPassowrd;
    }

    public void setmPassowrd(String mPassowrd) {
        this.mPassowrd = mPassowrd;
    }

    @Override
    public String toString() {
        return "Login{" +
                "mId=" + mId +
                ", mEmail='" + mEmail + '\'' +
                ", mPassowrd='" + mPassowrd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return getmId() == login.getmId() &&
                getmEmail().equals(login.getmEmail()) &&
                getmPassowrd().equals(login.getmPassowrd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmId(), getmEmail(), getmPassowrd());
    }
}
