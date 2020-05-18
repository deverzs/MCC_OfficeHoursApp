package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Login {

    private long mId;                   //Database assigned
    private String mEmail;              //Email of user
    private String mPassowrd;           //password of user
    private  int isProfessor;


    public Login(long mId, String mEmail, String mPassowrd, int isProfessor) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mPassowrd = mPassowrd;
        this.isProfessor = isProfessor;
    }


    public Login(String mEmail, String mPassowrd, int isProfessor) {
        this.mEmail = mEmail;
        this.mPassowrd = mPassowrd;
        this.isProfessor = isProfessor;
    }

    public int getIsProfessor() {
        return isProfessor;
    }

    public void setIsProfessor(int isProfessor) {
        this.isProfessor = isProfessor;
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
