package edu.miracostacollege.mcc_officehoursapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * SavedInstructor is one that a Login has saved
 */
public class SavedInstructor {

  private Instructor mInstructor;       //Instructor to save

    /**
     * Constructor
     * @param mInstructor instructor to save
     */
    public SavedInstructor(Instructor mInstructor) {
        this.mInstructor = mInstructor;
    }

    /**
     * Get the instructor
     * @return Instructor
     */
    public Instructor getmInstructor() {
        return mInstructor;
    }

    /**
     * Set the instructor to save
     * @param mInstructor Instructor
     */
    public void setmInstructor(Instructor mInstructor) {
        this.mInstructor = mInstructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedInstructor that = (SavedInstructor) o;
        return getmInstructor().equals(that.getmInstructor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmInstructor());
    }
}
