package de.exxcellent.challenge.csv;

import de.exxcellent.challenge.DataPoint;
import de.exxcellent.challenge.HandleDataPoint;

public class FootballDataPointGenerator extends DataPointGeneratorFromCSV {

    public FootballDataPointGenerator(String classpathResource, HandleDataPoint handleDataPoint) {
        super(classpathResource, handleDataPoint);
    }

    @Override
    public DataPoint createInitialDataPoint() {
        return DataPoint.createDataPointForFootball();
    }
}
