package de.exxcellent.challenge;

/**
 * create {@link DataPoint} from a source
 */
public interface DataPointGenerator {

    /**
     * get the next DataPoint, or null if there are none left
     *
     * @return
     */
    DataPoint getNextDataPoint();
}
