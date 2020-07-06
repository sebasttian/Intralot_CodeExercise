package com.ss.intralot_coding_exercise.services;

/**
 * Sebasttian Sobenes
 * YelpService.java
 */

import com.ss.intralot_coding_exercise.Constants;
import com.ss.intralot_coding_exercise.models.PhysicalTherapist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpService {
    public static void findPT(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<PhysicalTherapist> processResults(Response response) {
        ArrayList<PhysicalTherapist> physicalTherapists = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject yelpJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");

            int total = yelpJSON.getInt("total");

            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject ptJSON = businessesJSON.getJSONObject(i);
                String name = ptJSON.getString("name");
                double rating = ptJSON.getDouble("rating");
                int reviews = ptJSON.getInt("review_count");

                ArrayList<String> address = new ArrayList<>();
                JSONArray addressJSON = ptJSON.getJSONObject("location").getJSONArray("display_address");

                for (int j = 0; j < addressJSON.length(); j++) {
                    address.add(addressJSON.get(j).toString());
                }

                PhysicalTherapist physicalTherapist = new PhysicalTherapist(name, rating, reviews, address, total);
                physicalTherapists.add(physicalTherapist);
            }
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return physicalTherapists;
    }
}
