package de.exxcellent.challenge;

import java.io.InputStream;

public class FootballDataPointGenerator extends DataPointGeneratorFromCSV {

    public FootballDataPointGenerator(String classpathResource, HandleDataPoint handleDataPoint) {
        super(classpathResource, handleDataPoint);
    }

    @Override
    public DataPoint createInitialDataPoint() {
        return DataPoint.createDataPointForFootball();
    }
}
