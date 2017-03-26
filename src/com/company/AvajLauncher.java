package com.company;

import com.company.filehandler.FileManager;
import com.company.service.ValidateAndCreate;

import java.util.List;

/**
 * $find -name *.java > sources.txt
 * $javac -sourcepath @sources.txt
 */


public class AvajLauncher {

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        List<String> inputLines = FileManager.read(args[0]);

        ValidateAndCreate validateAndCreate = new ValidateAndCreate(inputLines);

        exitIfIsNotValid(validateAndCreate);

        WeatherTower weatherTower = new WeatherTower();

        registerFlyables(validateAndCreate.getFlyables(), weatherTower);

        changeWeather(validateAndCreate.getTimesWeatherChanges(), weatherTower);

        FileManager.write();

    }

    private static void exitIfIsNotValid(ValidateAndCreate validateAndCreate) {
        if (! validateAndCreate.fileIsValid()) {
            System.out.println("input file is not valid");
            System.exit(1);
        }
    }

    private static void changeWeather(int timesWeatherChanges, WeatherTower weatherTower) {
        for (int i = 0; i < timesWeatherChanges; i++) {
            weatherTower.changeWeather();
        }
    }

    private static void registerFlyables(List<Flyable> flyables, WeatherTower weatherTower) {
        for (Flyable flyable : flyables) {
            weatherTower.register(flyable);
            flyable.registerTower(weatherTower);
        }
    }
}
