package edu.miracostacollege.mcc_officehoursapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import edu.miracostacollege.mcc_officehoursapp.Model.SavedInstructor;

public class SavedInstructorListAdapter extends ArrayAdapter<SavedInstructor> {

    private Context mContext;
    private List<SavedInstructor> mSavedInstructorList;
    private int mResourceId;



    public SavedInstructorListAdapter(Context context, int resource, List<SavedInstructor> instructors) {
        super(context, resource, instructors);
        mContext = context;
        mResourceId = resource;
        mSavedInstructorList = instructors;

    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        final SavedInstructor savedInstructor = mSavedInstructorList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);
        LinearLayout   professorListLinearLayout = view.findViewById(R.id.professorListLinearLayout_LIST);
        TextView savedProfessorTextView = view.findViewById(R.id.professorNameTextView_LIST);
        professorListLinearLayout.setTag(savedInstructor);
        savedProfessorTextView.setText(savedInstructor.getmInstructor().getmFullName());

        return view;

    }




}
