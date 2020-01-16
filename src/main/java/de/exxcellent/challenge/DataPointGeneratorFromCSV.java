package de.exxcellent.challenge;

import javax.management.RuntimeMBeanException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public abstract class DataPointGeneratorFromCSV {

    public static final String CSV_SEPARATOR = ",";
    private String classPathResource;
    private InputStream inputStreamToCSV;
    private HandleDataPoint handleDataPoint;
    public DataPointGeneratorFromCSV (String classpathResource, HandleDataPoint handleDataPoint) {
        this.classPathResource = classpathResource;
        this.inputStreamToCSV = this.getClass().getResourceAsStream(this.classPathResource);
        this.handleDataPoint = handleDataPoint;

        readCSV();
    }

    public void readCSV() {

        if(this.inputStreamToCSV == null) {
            throw new RuntimeException("'"+this.classPathResource + "' cannot be found in the classpath");
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(this.inputStreamToCSV));
        String s;
        boolean readHeader = false;
        Map<String, Integer> columnIndex = new HashMap<>();
        try {
            while ((s = r.readLine()) != null) {
//                System.out.println(s);

                if (!readHeader) {
                    String[] headers = s.split(CSV_SEPARATOR);
                    for(int i = 0; i < headers.length; i++) {
                        columnIndex.put(headers[i], i);
                    }

                    readHeader = true;
//                    System.out.println(columnIndex);
                } else {
                    // read a line containing data
                    String[] lineValues = s.split(CSV_SEPARATOR);
//                    System.out.println(Arrays.asList(lineValues));

                    DataPoint dataPoint = createInitialDataPoint();

                    readIntoDataPoint(dataPoint, columnIndex, lineValues);

                    this.handleDataPoint.handleDataPoint(dataPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public abstract DataPoint createInitialDataPoint();

    private static void readIntoDataPoint(DataPoint dataPoint, Map<String, Integer> columnIndex, String[] lineValues) {
        dataPoint.setNameValue(lineValues[columnIndex.get(dataPoint.getName())]);
        dataPoint.setValue1(Integer.valueOf(lineValues[columnIndex.get(dataPoint.getVal1Name())]));
        dataPoint.setValue2(Integer.valueOf(lineValues[columnIndex.get(dataPoint.getVal2Name())]));
    }



}
