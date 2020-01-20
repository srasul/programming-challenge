package de.exxcellent.challenge.csv;

import de.exxcellent.challenge.DataPoint;
import de.exxcellent.challenge.HandleDataPoint;

public class WeatherDataPointGenerator extends DataPointGeneratorFromCSV {

    public WeatherDataPointGenerator(String classpathResource, HandleDataPoint handleDataPoint) {
        super(classpathResource, handleDataPoint);
    }

    @Override
    public DataPoint createInitialDataPoint() {
        return DataPoint.createDatePointForWeather();
    }
}
