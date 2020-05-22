package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

/**
 * Login that represents a new user, Student or Professor
 */
public class Login {

    private long mId;                   //Database assigned
    private String mEmail;              //Email of user
    private String mPassowrd;           //password of user
    private  int isProfessor;           //check if user is a Professor


    /**
     * The full constructor
     * @param mId   id of login
     * @param mEmail    email
     * @param mPassowrd  password
     * @param isProfessor   if Professor, 1
     */
    public Login(long mId, String mEmail, String mPassowrd, int isProfessor) {
        setmId(mId);
        this.mEmail = mEmail;
        this.mPassowrd = mPassowrd;
        setIsProfessor(isProfessor);
    }


    /**
     * Parial construcor before being added to database
     * @param mEmail email
     * @param mPassowrd password
     * @param isProfessor   if Professor, 1
     */
    public Login(String mEmail, String mPassowrd, int isProfessor) {
        this.mEmail = mEmail;
        this.mPassowrd = mPassowrd;
        setIsProfessor(isProfessor);
    }

    /**
     * Return whether Professor
     * @return 1 if Professor, 0 if not
     */
    public int getIsProfessor() {
        return isProfessor;
    }

    /**
     * Change the status of Professor
     * @param isProfessor  1 if Professor, 0 if Student
     */
    public void setIsProfessor(int isProfessor) {
        if(isProfessor >1 || isProfessor < 0) this.isProfessor = 0;
        else  this.isProfessor = isProfessor;
    }

    /**
     * Get the long id
     * @return id
     */
    public long getmId() {
        return mId;
    }

    /**
     * Set the id given by database
     * @param mId  database id
     */
    public void setmId(long mId) {
        this.mId = mId;
    }

    /**
     * Return the email
     * @return email
     */
    public String getmEmail() {
        return mEmail;
    }

    /**
     * Set the email address
     * @param mEmail String that is the email
     */
    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    /**
     * Return the password
     * @return password
     */
    public String getmPassowrd() {
        return mPassowrd;
    }

    /**
     * Set the password
     * @param mPassowrd password
     */
    public void setmPassowrd(String mPassowrd) {
        this.mPassowrd = mPassowrd;
    }

    @Override
    public String toString() {
        return "Login{" +
                "mId=" + mId +
                ", mEmail='" + mEmail + '\'' +
                ", mPassowrd='" + mPassowrd + '\'' +
                ", isProfessor=" + isProfessor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return getmId() == login.getmId() &&
                getIsProfessor() == login.getIsProfessor() &&
                getmEmail().equals(login.getmEmail()) &&
                getmPassowrd().equals(login.getmPassowrd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmId(), getmEmail(), getmPassowrd(), getIsProfessor());
    }
}
