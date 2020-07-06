package com.ss.intralot_coding_exercise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ss.intralot_coding_exercise.R;
import com.ss.intralot_coding_exercise.databinding.ActivityMainBinding;
import com.ss.intralot_coding_exercise.databinding.PhysicalTherapistListItemBinding;
import com.ss.intralot_coding_exercise.models.PhysicalTherapist;

import java.util.ArrayList;

/**
 * Sebasttian Sobenes
 * PhysicalTherapistListAdapter.java
 */

public class PhysicalTherapistListAdapter extends RecyclerView.Adapter<PhysicalTherapistListAdapter.PhysicalTherapistViewHolder> {
    private ArrayList<PhysicalTherapist> mPhysicalTherapists = new ArrayList<>();
    private Context mContext;

    public PhysicalTherapistListAdapter(Context context, ArrayList<PhysicalTherapist> physicalTherapists) {
        mContext = context;
        mPhysicalTherapists = physicalTherapists;
    }

    @NonNull
    @Override
    public PhysicalTherapistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.physical_therapist_list_item, parent, false);

        PhysicalTherapistViewHolder viewHolder = new PhysicalTherapistViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhysicalTherapistViewHolder holder, int position) {
        holder.bindPhysicalTherapist(mPhysicalTherapists.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhysicalTherapists.size();
    }

    public class PhysicalTherapistViewHolder extends RecyclerView.ViewHolder {
        private PhysicalTherapistListItemBinding binding;

        TextView mPTName = binding.physicalTherapistNameTextView;
        TextView mPTRating = binding.ratingTextView;



        private Context mContext;

        public PhysicalTherapistViewHolder(View itemView){
            super(itemView);
            //ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
        }

        public void bindPhysicalTherapist(PhysicalTherapist physicalTherapist) {
            String ratingText = "Rating: " + physicalTherapist.getRating() + "/5";
            mPTName.setText(physicalTherapist.getName());
            mPTRating.setText(ratingText);
        }
    }


}
