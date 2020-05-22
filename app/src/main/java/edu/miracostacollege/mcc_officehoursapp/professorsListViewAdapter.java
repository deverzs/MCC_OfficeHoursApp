package edu.miracostacollege.mcc_officehoursapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.List;

import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.R;

/**
 * List View Adapter for Professors for StudentSearch Activity
 */
public class professorsListViewAdapter extends ArrayAdapter<Instructor> {

    private Context mContext;
    private List<Instructor> mInstructorList;
    private int mResourceId;

    /**
     * Connects the list view adapter to the context
     * @param c  context
     * @param rId  resources
     * @param instructors  list
     */
    public professorsListViewAdapter(Context c, int rId, List<Instructor> instructors)
    {
        super(c, rId, instructors);
        mContext = c;
        mResourceId = rId;
        mInstructorList = instructors;

    }

    @NonNull
    @Override
    /**
     * Gets the view from the list to the list adapter
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //which instructor to get
        Instructor selectedInstructor = mInstructorList.get(position);
        //inflate the layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //wire up and set the tag
        LinearLayout professorsListView_SEARCH = view.findViewById(R.id.professorListLinearLayout_LIST);
        professorsListView_SEARCH.setTag(selectedInstructor);

        //fill in the list adapter views
        TextView professorNameTextView_LIST = view.findViewById(R.id.professorNameTextView_LIST);
        professorNameTextView_LIST.setText(selectedInstructor.getmFullName());

        return view;
    }


}


