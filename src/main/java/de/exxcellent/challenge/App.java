package de.exxcellent.challenge;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        System.out.println(Arrays.asList(args));


        MinSpreadFinder footballMinSpreadFinder = new MinSpreadFinder();
        FootballCSVGenerator footballCSVGenerator = new FootballCSVGenerator(App.class.getResourceAsStream("football.csv"), footballMinSpreadFinder);

        MinSpreadFinder weatherMinSpreadFinder = new MinSpreadFinder();
        WeatherCSVGenerator weatherCSVGenerator = new WeatherCSVGenerator(App.class.getResourceAsStream("weather.csv"), weatherMinSpreadFinder);

        // Your preparation code …

        String dayWithSmallestTempSpread = weatherMinSpreadFinder.getMinimumDataPoint().getNameValue();
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = footballMinSpreadFinder.getMinimumDataPoint().getNameValue();
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }

    private static void readIntoDataPoint(DataPoint min, Map<String, Integer> columnIndex, String[] lineValues) {
        min.setNameValue(lineValues[columnIndex.get(min.getName())]);
        min.setValue1(Integer.valueOf(lineValues[columnIndex.get(min.getVal1Name())]));
        min.setValue2(Integer.valueOf(lineValues[columnIndex.get(min.getVal2Name())]));
    }
}
