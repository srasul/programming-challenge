package de.exxcellent.challenge;

import de.exxcellent.challenge.csv.FootballDataPointGenerator;
import de.exxcellent.challenge.csv.WeatherDataPointGenerator;

/**
 * The starting point to do the calculation for the 'weather' and 'football' datasets. Run this class to see the result
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 *
 * TODO: handle command line args so that we can decide if we want to do football or weather case or both
 * TODO: allow paths of csv file to be specified via CLI args
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {


        MinSpreadFinder footballMinSpreadFinder = new MinSpreadFinder();
        new FootballDataPointGenerator("football.csv", footballMinSpreadFinder);

        MinSpreadFinder weatherMinSpreadFinder = new MinSpreadFinder();
        new WeatherDataPointGenerator("weather.csv", weatherMinSpreadFinder);

        // Your preparation code …

        String dayWithSmallestTempSpread = weatherMinSpreadFinder.getMinimumDataPoint().getNameValue();
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = footballMinSpreadFinder.getMinimumDataPoint().getNameValue();
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
