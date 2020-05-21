package edu.miracostacollege.mcc_officehoursapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

public class ScheduleListAdapter  extends ArrayAdapter<Schedule> {

    public static final String TAG = ScheduleListAdapter.class.getSimpleName();
    private Context mContext;
    private List<Schedule> mScheduleList;
    private int mResourceId;


    public ScheduleListAdapter(Context context, int resource, List<Schedule> schedules) {
        super(context, resource, schedules);
        mContext = context;
        mResourceId = resource;
        mScheduleList = schedules;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final Schedule selectedSchedule = mScheduleList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout scheduleListLinearLayout = view.findViewById(R.id.scheduleListLinearLayout_SCH_LIST);
        TextView dayTextView = view.findViewById(R.id.dayTextView_SCH_LIST);
        TextView timeTextView = view.findViewById(R.id.timeTextView_SCH_LIST);
        TextView locationTextView = view.findViewById(R.id.locationTextView_SCH_LIST);

        scheduleListLinearLayout.setTag(selectedSchedule);

        switch (selectedSchedule.getmOfficeHourDay())
        {
            case 0:
                dayTextView.setText("");
                break;
            case 1:
                dayTextView.setText("MON");
                break;
            case 2:
                dayTextView.setText("TUES");
            case 3:
                dayTextView.setText("WED");
                break;
            case 4:
                dayTextView.setText("THUR");
                break;
            case 5:
                dayTextView.setText("FRI");
                break;

        }
        String temp = "CANCELLED";
        timeTextView.setText(selectedSchedule.getmOfficeHourTime());
        if(selectedSchedule.getInSession()==1) {
            locationTextView.setText(selectedSchedule.getmOfficeHourLocation());
        }else if(selectedSchedule.getmOfficeHourDay() == 0){
            locationTextView.setText(selectedSchedule.getmOfficeHourLocation());
            timeTextView.setText("");
        }else if (selectedSchedule.getInSession()==0) {
            locationTextView.setText(temp);
        }
        return view;


    }
}
