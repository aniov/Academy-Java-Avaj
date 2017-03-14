package com.company.service;

import com.company.Coordinates;
import com.company.entity.WeatherType;

import java.util.Random;

/**
 * Created by Marius on 3/8/2017.
 */
public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        long sum = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + new Random().nextInt(3));
        return WeatherType.values()[(int)(sum % 4)].name();
    }

}
