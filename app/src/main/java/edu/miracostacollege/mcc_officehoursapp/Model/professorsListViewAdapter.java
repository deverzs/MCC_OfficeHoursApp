package edu.miracostacollege.mcc_officehoursapp.Model;

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

import edu.miracostacollege.mcc_officehoursapp.R;

public class professorsListViewAdapter extends ArrayAdapter<Instructor> {

    private Context mContext;
    private List<Instructor> mInstructorList;
    private int mResourceId;

    public professorsListViewAdapter(Context c, int rId, List<Instructor> instructors)
    {
        super(c, rId, instructors);
        mContext = c;
        mResourceId = rId;
        mInstructorList = instructors;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Instructor selectedInstructor = mInstructorList.get(position);


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(mResourceId, null);

        LinearLayout professorsListView_SEARCH = view.findViewById(R.id.professorsListView_SEARCH);
        TextView professorNameTextView_LIST = view.findViewById(R.id.professorNameTextView_LIST);



        professorNameTextView_LIST.setText(selectedInstructor.getmFullName());


        professorsListView_SEARCH.setTag(selectedInstructor);









        return view;
    }


}


