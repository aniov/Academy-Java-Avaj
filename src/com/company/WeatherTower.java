package com.company;

import com.company.service.WeatherProvider;

/**
 * Created by Marius on 3/8/2017.
 */
public class WeatherTower extends Tower {

    private WeatherProvider weatherProvider = WeatherProvider.getProvider();

    public String getWeather(Coordinates coordinates){
        return weatherProvider.getCurrentWeather(coordinates);
    }

    void changeWeather(){
        conditionsChanged();
    }
}
