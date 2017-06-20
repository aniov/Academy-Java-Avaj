package com.company.entity.model;

import com.company.*;
import com.company.entity.*;
import com.company.filehandler.FileManager;
import com.company.service.AircraftMessages;

/**
 * Created by Marius on 3/8/2017.
 */
public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private String currentWeather;
    private AircraftMessages aircraftMessages;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        aircraftMessages = AircraftMessages.getInstance();
    }

    @Override
    protected void initializeMoves() {
        moves.put(WeatherType.SUN.name(), new Coordinates(2, 0, 4));
        moves.put(WeatherType.RAIN.name(), new Coordinates(0, 0, -5));
        moves.put(WeatherType.FOG.name(), new Coordinates(0, 0, -3));
        moves.put(WeatherType.SNOW.name(), new Coordinates(0, 0, -15));
    }

    @Override
    public void updateConditions() {
        currentWeather = weatherTower.getWeather(coordinates);
        Coordinates coord = moves.get(currentWeather.toUpperCase());
        changeCoordinates(coord.getLongitude(), coord.getLatitude(), coord.getHeight());
        FileManager.addMessage(this + aircraftMessages.getBaloonMessages().get(currentWeather.toUpperCase()));

        if (isLanding()){
            FileManager.addMessage(this + " landing.");
             weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String toString() {
        return AirCraftType.BALOON.getType() + super.toString();
    }

}
