package com.ss.intralot_coding_exercise.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.ss.intralot_coding_exercise.databinding.ActivityMainBinding;
import com.ss.intralot_coding_exercise.models.PhysicalTherapist;
import com.ss.intralot_coding_exercise.services.YelpService;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        final ListView mSummary =  binding.lwSummary;

        Button mDisplayResults = binding.btnDisplayResults;
        mDisplayResults.setOnClickListener(new View.OnClickListener() {

            EditText mLocation = binding.etEnterLocation;
            @Override
            public void onClick(View view) {
                getPT(mLocation.getText().toString(), mSummary);
            }
        });

    }

    private void getPT(String location, final ListView _summary) {
        final YelpService yelpService = new YelpService();
        yelpService.findPT(location, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
                    physicalTherapists = yelpService.processResults(response);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] physicalTherapistNames = new String[physicalTherapists.size()];
                            for (int i = 0; i < physicalTherapistNames.length; i++) {
                                physicalTherapistNames[i] = physicalTherapists.get(i).getName();
                            }

                            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                                    android.R.layout.simple_list_item_1, physicalTherapistNames);
                            _summary.setAdapter(adapter);

                            for(PhysicalTherapist physicalTherapist: physicalTherapists) {
                                Log.d(TAG, "Name: " + physicalTherapist.getName());
                                Log.d(TAG, "Review: " + Integer.toString(physicalTherapist.getReviews()));
                                Log.d(TAG, "Rating: " + Double.toString(physicalTherapist.getRating()));
                                Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", physicalTherapist.getAddress()));
                            }
                        }
                    });
            }
        });
    }
}
