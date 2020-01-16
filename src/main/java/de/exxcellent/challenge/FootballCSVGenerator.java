package de.exxcellent.challenge;

import java.io.InputStream;

public class FootballCSVGenerator extends DataPointGeneratorFromCSV {

    public FootballCSVGenerator(InputStream inputStream, HandleDataPoint handleDataPoint) {
        super(inputStream, handleDataPoint);
    }

    @Override
    public DataPoint createInitialDataPoint() {
        return DataPoint.createDataPointForFootball();
    }
}
