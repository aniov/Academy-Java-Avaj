package com.company;

import com.company.filehandler.FileManager;
import com.company.service.ValidateAndCreate;

import java.util.ArrayList;
import java.util.List;

/**
* $find -name *.java > sources.txt
* $javac -sourcepath @sources.txt
* */


public class AvajLauncher {

    public static void main(String[] args) {
        if (args.length != 1){
            return;
        }

        List<String> inputLines = FileManager.read(args[0]);
        List<Flyable> flyables = new ArrayList<>();
        ValidateAndCreate validateAndCreate = new ValidateAndCreate(inputLines, flyables);

        if (inputLines.isEmpty() || ! validateAndCreate.fileIsValid()){
            System.out.println("input file is not valid");
            return;
        }

        WeatherTower weatherTower = new WeatherTower();
        for (Flyable flyable : flyables){
            weatherTower.register(flyable);
            flyable.registerTower(weatherTower);
        }

        for (int i = 0; i < validateAndCreate.getTimesWeatherChanges(); i++){
            weatherTower.changeWeather();
        }

        FileManager.write();

    }
}
