package com.company.service;

import com.company.Flyable;
import com.company.entity.AirCraftType;
import com.company.entity.AircraftFactory;
import com.company.exceptions.MyNumberFormatException;
import com.company.exceptions.MyTypeAndNameException;
import com.company.service.md5encryptor.EncryptMD5;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Marius on 3/8/2017.
 */
public class ValidateAndCreate {

    private List<String> inputLines;
    private List<String> inputMD5DecodedLines;
    private List<Flyable> flyables;
    public int timesWeatherChanges;
    private String type;
    private String name;
    private int longitude;
    private int latitude;
    private int height;

    public ValidateAndCreate(List<String> inputLines, List<Flyable> flyables) {
        this.inputLines = inputLines;
        this.flyables = flyables;
    }

    public boolean fileIsValid(){
        if (entriesAreValid(inputLines) || md5EntriesAreValid()){
            return true;
        }
        return false;
    }

    private boolean md5EntriesAreValid() {

        inputMD5DecodedLines = new ArrayList<>();
        EncryptMD5 encryptMD5 = new EncryptMD5();

        String firstLine = encryptMD5.getIntegerValues(inputLines.get(0));
        inputMD5DecodedLines.add(firstLine);

        ListIterator<String> iterator = inputLines.listIterator();
        iterator.next();

        while (iterator.hasNext()){
            String[] elements = iterator.next().split("\\s+");
            if (elements.length != 5){
                System.out.println("Invalid number of parameters for line: " + iterator.nextIndex());
                return false;
            }
            /** elements in a line*/
            StringBuilder newLine = new StringBuilder();
            String type = encryptMD5.getTypeValue(elements[0]);

            newLine.append(type + " ");
            String name = encryptMD5.getNameValue(elements[1], type);

            newLine.append(name + " ");
            newLine.append(encryptMD5.getIntegerValues(elements[2]) + " ");
            newLine.append(encryptMD5.getIntegerValues(elements[3]) + " ");
            newLine.append(encryptMD5.getIntegerValues(elements[4]) + "\n");

            inputMD5DecodedLines.add(newLine.toString());
        }

        if (entriesAreValid(inputMD5DecodedLines)){
            return true;
        }
        return false;
    }

    public boolean entriesAreValid(List<String> input) {

        /** First line*/
        try {
            timesWeatherChanges = Integer.parseInt(input.get(0));
        } catch (NumberFormatException e){
            //System.out.println("First line is not valid");
            return false;
        }
        ListIterator<String> iterator = input.listIterator();

        iterator.next(); //We skip first line
        /** Rest of file*/
        while(iterator.hasNext()){
            String line = iterator.next();

            try {
                if (validateLine(line, iterator.nextIndex() + 1)){
                    Flyable flyable = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
                    flyables.add(flyable);
                }
            } catch (MyNumberFormatException | MyTypeAndNameException e) {
                System.out.println(e);
                return false;
            }
        }

        return true;
    }

    private boolean validateLine(String line, int lineNr) throws MyNumberFormatException, MyTypeAndNameException {

        String[] elements = line.split("\\s+");
        if (elements.length != 5){
            System.out.println("Invalid number of parameters for line: " + lineNr);
            return false;
        }

        type = elements[0];
        name = elements[1]; /** Nothing to validate here*/
        try {
            longitude = Integer.parseInt(elements[2]);
            latitude = Integer.parseInt(elements[3]);
            height = Integer.parseInt(elements[4]);
        } catch (NumberFormatException e){
            System.out.println("longitude or latitude or height are not valid on line: " + lineNr + "\nException: " + e);
            return false;
        }
        if (longitude < 0 || latitude < 0 || height < 0){
            throw new MyNumberFormatException("Coordinate are negative");
        }
        if (!type.equals(AirCraftType.BALOON.getType()) && !type.equals(AirCraftType.HELICOPTER.getType())
                && !type.equals(AirCraftType.JETPLANE.getType())){
            throw new MyTypeAndNameException("Type is not valid");
        }
        return true;
    }

    public int getTimesWeatherChanges() {
        return timesWeatherChanges;
    }
}
