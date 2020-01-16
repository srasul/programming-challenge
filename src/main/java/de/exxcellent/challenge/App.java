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

        DataPoint min = DataPoint.createDatePointForWeather();
        try {
            InputStream is = App.class.getResourceAsStream("weather.csv");
//            InputStream is = App.class.getResourceAsStream("football.csv");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String s;
            boolean readHeader = false;
            Map<String, Integer> columnIndex = new HashMap<>();
            while ((s = r.readLine()) != null) {
                System.out.println(s);

                if(!readHeader) {
                    StringTokenizer tokenizer = new StringTokenizer(s,",");
                    int colValue = 0;
                    while (tokenizer.hasMoreElements()) {
                        columnIndex.put(tokenizer.nextToken(), colValue);
                        colValue++;
                    }
                    readHeader = true;
                    System.out.println(columnIndex);
                }else {
                    // read a line containing data
                    String[] lineValues = s.split(",");
                    System.out.println(Arrays.asList(lineValues));

                    if(!min.hasValues()) {
                        readIntoDataPoint(min, columnIndex, lineValues);
                    }else {
                        DataPoint nextReadLineDataPoint = DataPoint.createDatePointForWeather();
                        readIntoDataPoint(nextReadLineDataPoint, columnIndex, lineValues);

                        min = DataPoint.getMinimum(min, nextReadLineDataPoint);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Your preparation code …

        String dayWithSmallestTempSpread = min.getSpread().toString() + " at " + min.getNameValue();     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", min.getNameValue());
    }

    private static void readIntoDataPoint(DataPoint min, Map<String, Integer> columnIndex, String[] lineValues) {
        min.setNameValue(lineValues[columnIndex.get(min.getName())]);
        min.setValue1(Integer.valueOf(lineValues[columnIndex.get(min.getVal1Name())]));
        min.setValue2(Integer.valueOf(lineValues[columnIndex.get(min.getVal2Name())]));
    }
}
