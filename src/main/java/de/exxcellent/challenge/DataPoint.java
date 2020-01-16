package de.exxcellent.challenge;


public class DataPoint {


    public static DataPoint createDatePointForWeather() {
        return new DataPoint("Day", "MxT", "MnT");
    }

    public static DataPoint createDataPointForFootball() {
        return new DataPoint("Team", "Goals", "Goals Allowed");
    }


    public static DataPoint getMinimum(DataPoint currentMinimum, DataPoint anotherOne) {
        // TODO: what happends when they are the same?
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
