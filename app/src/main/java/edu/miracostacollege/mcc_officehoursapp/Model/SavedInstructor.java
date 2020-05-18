package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class SavedInstructor {

    private long mId;                   //Database assigned
    private int mInstructorCode;        //Unique code for the instructor


    public SavedInstructor(long mId) {
        this.mId = mId;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public int getmInstructorCode() {
        return mInstructorCode;
    }

    public void setmInstructorCode(int mInstructorCode) {
        this.mInstructorCode = mInstructorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedInstructor that = (SavedInstructor) o;
        return getmId() == that.getmId() &&
                getmInstructorCode() == that.getmInstructorCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmId(), getmInstructorCode());
    }

    @Override
    public String toString() {
        return "SavedInstructor{" +
                "mId=" + mId +
                ", mInstructorCode=" + mInstructorCode +
                '}';
    }
}
