package com.company.filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marius on 3/8/2017.
 */
public class FileManager {

    private static final String outputFile = "simulation.txt";
    private static List<String> messages = new ArrayList<>();

    public static void addMessage(String message) {
        messages.add(message);
    }

    public static List<String> read(String file){

        Scanner scanner;
        List<String> inputLines = new ArrayList<>();

        File fileName = new File(file);
        try {
            scanner = new Scanner(fileName);
            while (scanner.hasNext()){
                inputLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found: " + e);
            return inputLines;
        }
        scanner.close();
        return inputLines;
    }

    public static void write() {

        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            for (String line : messages){
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to 'simulation.txt' " + e);
        }
    }

}
