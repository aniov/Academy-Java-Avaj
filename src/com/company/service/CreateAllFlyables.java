package com.company.service;

import com.company.entity.AircraftFactory;
import com.company.Flyable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 3/8/2017.
 */
public class CreateAllFlyables {

    public static int timesWeatherChanges;
    private String type;
    private String name;
    private int longitude;
    private int latitude;
    private int height;

    public List<Flyable> createAll(List<String> inputLines) {

        List<Flyable> flyables = new ArrayList<>();

        timesWeatherChanges = Integer.parseInt(inputLines.get(0));
        inputLines.remove(0);

        for (String line : inputLines){
            initialize(line);
            Flyable flyable = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
            flyables.add(flyable);
        }
        return flyables;
    }

    private void initialize(String line){

        String[] elements = line.split("\\s+");
        try {
            type = elements[0];
            name = elements[1];
            longitude = Integer.parseInt(elements[2]);
            latitude = Integer.parseInt(elements[3]);
            height = Integer.parseInt(elements[4]);
        } catch (Exception e){
            System.out.println("Data format unsupported: " + e);
        }

    }
}
