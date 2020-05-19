package edu.miracostacollege.mcc_officehoursapp.Model;

import java.util.Objects;

public class Status {

    private  long mId;
    private Schedule mSchedule;
    private int mStatus;

    public Status(Schedule mSchedule, int mStatus) {
        //this.mId = mId;
        this.mSchedule = mSchedule;
        this.mStatus = mStatus;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public Schedule getmSchedule() {
        return mSchedule;
    }

    public void setmSchedule(Schedule mSchedule) {
        this.mSchedule = mSchedule;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    @Override
    public String toString() {
        return "Status{" +
                "mSchedule=" + mSchedule +
                ", mStatus=" + mStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return getmStatus() == status.getmStatus() &&
                getmSchedule().equals(status.getmSchedule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmSchedule(), getmStatus());
    }
}
