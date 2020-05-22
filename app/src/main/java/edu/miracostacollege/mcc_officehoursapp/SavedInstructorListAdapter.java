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

/**
 * SavedInstructor list adapter class
 */
public class SavedInstructorListAdapter extends ArrayAdapter<SavedInstructor> {

    private Context mContext;
    private List<SavedInstructor> mSavedInstructorList;
    private int mResourceId;

    /**
     * Connects the list view adapter to the context
     * @param context  context
     * @param resource  resources
     * @param instructors  list
     */
    public SavedInstructorListAdapter(Context context, int resource, List<SavedInstructor> instructors) {
        super(context, resource, instructors);
        mContext = context;
        mResourceId = resource;
        mSavedInstructorList = instructors;

    }

    @Override
    /**
     * Gets the view from the list to the list adapter
     */
    public View getView(int pos, View convertView, ViewGroup parent){

        //which instructor to get
        final SavedInstructor savedInstructor = mSavedInstructorList.get(pos);
        //inflate the layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //wire up and set the tag and populate the list
        LinearLayout   professorListLinearLayout = view.findViewById(R.id.professorListLinearLayout_LIST_SAVED);
        TextView savedProfessorTextView = view.findViewById(R.id.professorNameTextView_LIST_SAVED);
        professorListLinearLayout.setTag(savedInstructor);
        savedProfessorTextView.setText(savedInstructor.getmInstructor().getmFullName());

        return view;
    }

}
