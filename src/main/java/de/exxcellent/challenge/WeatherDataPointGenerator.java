package de.exxcellent.challenge;

import java.io.InputStream;

public class WeatherDataPointGenerator extends DataPointGeneratorFromCSV {

    public WeatherDataPointGenerator(String classpathResource, HandleDataPoint handleDataPoint) {
        super(classpathResource, handleDataPoint);
    }

    @Override
    public DataPoint createInitialDataPoint() {
        return DataPoint.createDatePointForWeather();
    }
}
