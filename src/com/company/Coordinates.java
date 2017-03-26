package com.company;

/**
 * Created by Marius on 3/8/2017.
 */
public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;
    private final int MAX_HEIGHT = 100;
    private final int MIN_HEIGHT = 0;


    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        if (height > MAX_HEIGHT){
            this.height = MAX_HEIGHT;
        } else {
            this.height = height;
        }
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void addToCoordinates(int d_long, int d_lat, int d_h){

       if ((double)longitude + d_long > Integer.MAX_VALUE){
           longitude = Integer.MAX_VALUE;
       } else if (longitude + d_long < 0){
           longitude = 0;
       } else {
           longitude += d_long;
       }

        if ((double)latitude + d_lat > Integer.MAX_VALUE){
            latitude = Integer.MAX_VALUE;
        } else if (latitude + d_lat < 0){
            latitude = 0;
        } else {
            latitude += d_lat;
        }

        if (height + d_h > MAX_HEIGHT){
            height = MAX_HEIGHT;
        } else if (height + d_h < MIN_HEIGHT){
            height = MIN_HEIGHT;
        } else {
            height += d_h;
        }

    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", height=" + height +
                '}';
    }
}
