package de.exxcellent.challenge;


/**
 * class represents the row values in a CSV that we are interested in. For each data item, we have it's
 * name (taken from the 1st line of the csv), and name of the 2 values we are interested in. Later on as we
 * read the CSV, we will fill in the values
 *
 * TODO: currently all values we are interested in are {@link Integer}s. What if the values we need have decimals?
 * TODO: what if two data points have the same spread... how do we handle this case?
 * TODO: how do we handle CSV where the value we need is missing
 *
 */
public class DataPoint {

    /**
     * static factory method to create a data point for the weather csv file
     *
     * @return DataPoint where when reading the CSV, we read the Day, MxT and MnT values
     */
    public static DataPoint createDatePointForWeather() {
        return new DataPoint("Day", "MxT", "MnT");
    }

    /**
     * static factory method to get a datapoint for the football csv files
     *
     * @return DataPoint where when reading the CSV, we read the 'Team', 'Goals' and 'Goals Allowed' values
     */
    public static DataPoint createDataPointForFootball() {
        return new DataPoint("Team", "Goals", "Goals Allowed");
    }


    public static DataPoint getMinimum(DataPoint currentMinimum, DataPoint anotherOne) {
        // TODO: what happens when they are the same?
        if(anotherOne.getSpread() < currentMinimum.getSpread())
            return anotherOne;
        else return currentMinimum;
    }

    private String name;
    private String nameValue;

    private String val1Name;
    private Integer value1;

    private String val2Name;
    private Integer value2;

    public DataPoint(String name, String val1Name, String val2Name) {
        this.name = name;
        this.val1Name = val1Name;
        this.val2Name = val2Name;
    }

    public Integer getSpread() {
        return Math.abs(this.getValue1() - this.getValue2());
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    boolean hasValues() {
        return value1 != null && value2 != null;
    }

    public String getVal1Name() {
        return val1Name;
    }

    public String getVal2Name() {
        return val2Name;
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    public String getName() {
        return name;
    }
}
