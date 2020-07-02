package com.ss.intralot_coding_exercise.models;

import java.util.ArrayList;

/**
 * Sebasttian Sobenes
 * PhysicalTherapist.java
 */

public class PhysicalTherapist {
    private String name;
    private double rating;
    private int reviews;
    private ArrayList<String> address = new ArrayList<>();

    public PhysicalTherapist(String name, double rating, int reviews, ArrayList<String> address) {
        this.name = name;
        this.rating = rating;
        this.reviews = reviews;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public int getReviews() {
        return reviews;
    }

    public ArrayList<String> getAddress() {
        return address;
    }
}
