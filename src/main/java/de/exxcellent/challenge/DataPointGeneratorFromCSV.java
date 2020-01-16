package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public abstract class DataPointGeneratorFromCSV {

    private InputStream inputStreamToCSV;
    private HandleDataPoint handleDataPoint;
    public DataPointGeneratorFromCSV (InputStream inputStream, HandleDataPoint handleDataPoint) {
        this.inputStreamToCSV = inputStream;
        this.handleDataPoint = handleDataPoint;

        readCSV();
    }

    public void readCSV() {
        BufferedReader r = new BufferedReader(new InputStreamReader(this.inputStreamToCSV));
        String s;
        boolean readHeader = false;
        Map<String, Integer> columnIndex = new HashMap<>();
        try {
            while ((s = r.readLine()) != null) {
//                System.out.println(s);

                if (!readHeader) {
                    StringTokenizer tokenizer = new StringTokenizer(s, ",");
                    int colValue = 0;
                    while (tokenizer.hasMoreElements()) {
                        columnIndex.put(tokenizer.nextToken(), colValue);
                        colValue++;
                    }
                    readHeader = true;
//                    System.out.println(columnIndex);
                } else {
                    // read a line containing data
                    String[] lineValues = s.split(",");
//                    System.out.println(Arrays.asList(lineValues));

                    DataPoint dataPoint = createInitialDataPoint();

                    readIntoDataPoint(dataPoint, columnIndex, lineValues);

                    this.handleDataPoint.handleDataPoint(dataPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract DataPoint createInitialDataPoint();

    private static void readIntoDataPoint(DataPoint dataPoint, Map<String, Integer> columnIndex, String[] lineValues) {
        dataPoint.setNameValue(lineValues[columnIndex.get(dataPoint.getName())]);
        dataPoint.setValue1(Integer.valueOf(lineValues[columnIndex.get(dataPoint.getVal1Name())]));
        dataPoint.setValue2(Integer.valueOf(lineValues[columnIndex.get(dataPoint.getVal2Name())]));
    }



}
