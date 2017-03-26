package com.company.entity;

import com.company.WeatherTower;

/**
 * Created by Marius on 3/8/2017.
 */
public interface Flyable {

    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}
