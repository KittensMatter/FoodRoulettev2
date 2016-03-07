package com.example.ray.foodroulettev2;

/**
 * Created by raylee on 2/25/16.
 */
public class Place {
    private String place;
    private String address;
    private int value;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Place: " + place + "\nLocation: " + address;
    }
}
