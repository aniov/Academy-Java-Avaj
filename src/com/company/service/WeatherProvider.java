package com.company.service;

import com.company.entity.Coordinates;
import com.company.entity.WeatherType;

import java.util.Random;

/**
 * Created by Marius on 3/8/2017.
 */
public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();

    private static String[] weather; /** Using an ENUM: WeatherType*/

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        long sum = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + new Random().nextInt(3));
        return WeatherType.values()[(int)(Math.abs(sum) % 4)].name();
    }

}
