package de.exxcellent.challenge;

import java.io.InputStream;

public class WeatherCSVGenerator extends DataPointGeneratorFromCSV {

    public WeatherCSVGenerator(InputStream inputStream, HandleDataPoint handleDataPoint) {
        super(inputStream, handleDataPoint);
    }

    @Override
    public DataPoint createInitialDataPoint() {
        return DataPoint.createDatePointForWeather();
    }
}
