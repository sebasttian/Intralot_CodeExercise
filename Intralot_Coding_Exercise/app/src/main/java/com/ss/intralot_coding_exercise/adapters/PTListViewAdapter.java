package com.ss.intralot_coding_exercise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ss.intralot_coding_exercise.R;
import com.ss.intralot_coding_exercise.databinding.ActivityMainBinding;
import com.ss.intralot_coding_exercise.databinding.PhysicalTherapistListItemBinding;
import com.ss.intralot_coding_exercise.models.PhysicalTherapist;

import java.util.ArrayList;

/**
 * Sebasttian Sobenes
 * PTListViewAdapter.java
 */

public class PTListViewAdapter extends ArrayAdapter<PhysicalTherapist> {

    private ArrayList<PhysicalTherapist> mPhysicalTherapists;
    Context mContext;

    private static class ViewHolder {
        TextView mPTName;
        TextView mPTRating;
        TextView mPTReview;
        TextView mPTAddress;
    }

    public PTListViewAdapter(ArrayList<PhysicalTherapist> _physicalTherapists, Context context) {
        super(context, R.layout.physical_therapist_list_item, _physicalTherapists);
        this.mPhysicalTherapists = _physicalTherapists;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PhysicalTherapist mPhysicalTherapist = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.physical_therapist_list_item, parent, false);

            viewHolder.mPTName = (TextView) convertView.findViewById(R.id.physicalTherapist_name_TextView);
            viewHolder.mPTReview = (TextView) convertView.findViewById(R.id.reviewTextView);
            viewHolder.mPTRating = (TextView) convertView.findViewById(R.id.ratingTextView);
            viewHolder.mPTAddress = (TextView) convertView.findViewById(R.id.addressTextView);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.mPTName.setText(mPhysicalTherapist.getName());
        viewHolder.mPTReview.setText("Reviews: " + mPhysicalTherapist.getReviews());
        viewHolder.mPTRating.setText("Rating: " + Double.toString(mPhysicalTherapist.getRating()) + "/5");
        viewHolder.mPTAddress.setText("Address: " + mPhysicalTherapist.getAddress());
        return convertView;
    }
}
