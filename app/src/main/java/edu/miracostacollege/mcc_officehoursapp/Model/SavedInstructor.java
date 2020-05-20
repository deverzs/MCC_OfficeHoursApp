package edu.miracostacollege.mcc_officehoursapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class SavedInstructor {

  private Instructor mInstructor;

    public SavedInstructor(Instructor mInstructor) {
        this.mInstructor = mInstructor;
    }

    public Instructor getmInstructor() {
        return mInstructor;
    }

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
