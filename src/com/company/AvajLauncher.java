package com.company;

import com.company.entity.Flyable;
import com.company.filehandler.FileManager;
import com.company.service.ValidateAndCreate;

import java.util.List;

/**
 * Compile using:
 * $find -name *.java > sources.txt
 * $javac -sourcepath @sources.txt
 * Run:
 * $java name source.txt
 *
 * find . -name \*.java > sources.txt
 * javac -sourcepath . @sources.txt
 * Run:
 * java -classpath . com.company.AvajLauncher scenario.txt
 *
 *
 */

public class AvajLauncher {

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }
        /** Read from input file*/
        List<String> inputLines = FileManager.read(args[0]);

        ValidateAndCreate validateAndCreate = new ValidateAndCreate(inputLines);
        /** Check the file if is valid, if Yes we can continue, if No we exit the program*/
        exitIfIsNotValid(validateAndCreate);

        WeatherTower weatherTower = new WeatherTower();
        /** Create flyable objects, register them the the Subject (Tower) list, set too all of them same weatherTower instance*/
        registerFlyables(validateAndCreate.getFlyables(), weatherTower);
        /** Change the weather as many times as we read from the input file*/
        changeWeather(validateAndCreate.getTimesWeatherChanges(), weatherTower);
        /** Write all the messages from Tower, Aircraft's, in the output file*/
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
