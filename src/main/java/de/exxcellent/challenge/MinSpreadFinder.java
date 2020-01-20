package de.exxcellent.challenge;

/**
 * TODO: this class is not thread-safe
 */
public class MinSpreadFinder implements HandleDataPoint {

    private DataPoint minimumDataPoint;

    public void handleDataPoint(DataPoint dataPoint) {
        if(minimumDataPoint == null)
            minimumDataPoint = dataPoint;
        else {
            minimumDataPoint = DataPoint.getMinimum(minimumDataPoint, dataPoint);
        }
    }

    public DataPoint getMinimumDataPoint() {
        return minimumDataPoint;
    }
}
