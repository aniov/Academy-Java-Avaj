package com.company.entity;

import com.company.Coordinates;
import com.company.Flyable;
import com.company.entity.Baloon;
import com.company.entity.Helicopter;
import com.company.entity.JetPlane;

/**
 * Created by Marius on 3/8/2017.
 */
public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height){

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type){

            case "Baloon" : return new Baloon(name, coordinates);
            case "Helicopter" : return new Helicopter(name, coordinates);
            case "JetPlane" : return new JetPlane(name, coordinates);
            default: return null;
        }

    }
}
