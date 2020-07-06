package com.ss.intralot_coding_exercise.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.ss.intralot_coding_exercise.R;
import com.ss.intralot_coding_exercise.adapters.PTListViewAdapter;
import com.ss.intralot_coding_exercise.databinding.ActivityMainBinding;
import com.ss.intralot_coding_exercise.models.PhysicalTherapist;
import com.ss.intralot_coding_exercise.services.YelpService;

import net.mintern.primitive.Primitive;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    public ArrayList<PhysicalTherapist> physicalTherapists = new ArrayList<>();
    private PTListViewAdapter ptLWAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final ListView mPTList = binding.lwListOfPT;
        final TextView mTotal = binding.tvTotal;
        final TextView mTotalRating = binding.tvTotalWithRatings;
        final TextView mNumberOfPT = binding.tvNumberOfPT;
        final TextView mTotalReviews = binding.tvTotalReview;

        Button mDisplayResults = binding.btnDisplayResults;
        mDisplayResults.setOnClickListener(new View.OnClickListener() {

            EditText mLocation = binding.etEnterLocation;

            @Override
            public void onClick(View view) {
                getPT(mLocation.getText().toString(), mPTList, mTotal, mTotalRating, mNumberOfPT, mTotalReviews);
            }
        });

    }

    private void getPT(String location, final ListView _listOfPT, final TextView _total, final TextView _totalWithRatings, final TextView _numberOfPT, final TextView _totalReview) {
        final YelpService yelpService = new YelpService();
        yelpService.findPT(location, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    physicalTherapists = yelpService.processResults(response);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            String[] ptRatingAverage = new String[physicalTherapists.size()];
                            double ratingAverage = 0 ;
                            int total = 0;
                            int totalReviews = 0;

                            double[] ratingArray = new double[ptRatingAverage.length];

                            for (int i = 0; i < ptRatingAverage.length; i++) {
                                ratingAverage += physicalTherapists.get(i).getRating();
                                total = physicalTherapists.get(i).getTotal();
                                totalReviews += physicalTherapists.get(i).getReviews();
                                ratingArray[i] = physicalTherapists.get(i).getRating();
                                Log.d(TAG, "run: " + ratingArray[i]);
                            }

                            Primitive.sort(ratingArray,(d1, d2) -> Double.compare(d2, d1), false);

                            double avg = ratingAverage / ptRatingAverage.length;

                            String totalPTText = getString(R.string.total, total);
                            String averagePTRatingText = getString(R.string.totalwithratings, avg);

                            _numberOfPT.setText(getString(R.string.number_of_pt, total));
                            _total.setText(totalPTText);
                            _totalWithRatings.setText(averagePTRatingText);
                            _totalReview.setText(getString(R.string.total_reviews, totalReviews));

                            ptLWAdapter = new PTListViewAdapter(physicalTherapists, getApplicationContext());
                            _listOfPT.setAdapter(ptLWAdapter);
                        }
                    });
            }
        });
    }
}
