package de.exxcellent.challenge.csv;

import de.exxcellent.challenge.App;
import de.exxcellent.challenge.DataPoint;
import de.exxcellent.challenge.HandleDataPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * read the parse the CSV files that are in the classpath. Throws {@link RuntimeException}s if the CSV file is missing
 * or corrupt.
 *
 * TODO: what if the CSV file is very big? then we would need to do the readCSV method in the background
 * TODO: Logging
 */
public abstract class DataPointGeneratorFromCSV {

    private static final String CSV_SEPARATOR = ",";
    private String classPathResource;
    private InputStream inputStreamToCSV;
    private HandleDataPoint handleDataPoint;

    /**
     * create a CSV {@link DataPoint} generator
     *
     * @param classpathResource the name of the csv file that is in the classpath
     * @param handleDataPoint For each {@link DataPoint}, this interface's one method is called is called to handle that
     *                        <pre>DataPoint</pre>
     */
    public DataPointGeneratorFromCSV (String classpathResource, HandleDataPoint handleDataPoint) {
        this.classPathResource = classpathResource;
        this.inputStreamToCSV = App.class.getResourceAsStream(this.classPathResource);
        this.handleDataPoint = handleDataPoint;

        readCSV();
    }

    public void readCSV() {

        if(this.inputStreamToCSV == null) {
            // missing CSV
            throw new RuntimeException("'"+this.classPathResource + "' cannot be found in the classpath");
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(this.inputStreamToCSV));
        String s;
        boolean readHeader = false;
        int numHeaders = 0;
        Map<String, Integer> columnIndex = new HashMap<>();
        try {
            while ((s = r.readLine()) != null) {
                if (!readHeader) {
                    String[] headers = s.split(CSV_SEPARATOR);

                    for(int i = 0; i < headers.length; i++) {
                        columnIndex.put(headers[i], i);
                    }

                    numHeaders = headers.length;
                    readHeader = true;
                } else {
                    // read a line containing data
                    String[] lineValues = s.split(CSV_SEPARATOR);

                    if(lineValues.length != numHeaders) {
                        // corrupt CSV
                         String error = classPathResource + " may be corrupt. Expecting "
                                 + numHeaders +" values, but got only "
                                 + lineValues.length + " for this line: " + s;
                         throw new RuntimeException(error);
                    }

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

        // columnIndex is a map that contains the column name and it's index (starting from 0)

        // lineValues is one row of a CSV file but as a array of strings

        // dataPoints has the names of the columns that we are interested in reading

        // so given the column name, we lookup it's index in the columnIndex map
        // and then find the value using that index in lineValues

        dataPoint.setNameValue(lineValues[columnIndex.get(dataPoint.getName())]);
        dataPoint.setValue1(Integer.valueOf(lineValues[columnIndex.get(dataPoint.getVal1Name())]));
        dataPoint.setValue2(Integer.valueOf(lineValues[columnIndex.get(dataPoint.getVal2Name())]));
    }



}
