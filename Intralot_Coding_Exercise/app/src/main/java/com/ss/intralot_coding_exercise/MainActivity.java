package com.ss.intralot_coding_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ss.intralot_coding_exercise.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Button mDisplayResults = binding.btnDisplayResults;
        mDisplayResults.setOnClickListener(new View.OnClickListener() {

            EditText mLocation = binding.etEnterLocation;
            @Override
            public void onClick(View view) {
                getPT(mLocation.getText().toString());
            }
        });

    }

    private void getPT(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findPT(location, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
