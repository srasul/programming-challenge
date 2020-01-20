package de.exxcellent.challenge;

import de.exxcellent.challenge.csv.FootballDataPointGenerator;
import de.exxcellent.challenge.csv.WeatherDataPointGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void testMissingCSV() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            MinSpreadFinder footballMinSpreadFinder = new MinSpreadFinder();
            FootballDataPointGenerator footballDataPointGenerator = new FootballDataPointGenerator("1football.csv", footballMinSpreadFinder);
        });
    }

    @Test
    void testEmptyCSV() {
        MinSpreadFinder minSpreadFinder = new MinSpreadFinder();
        FootballDataPointGenerator footballDataPointGenerator = new FootballDataPointGenerator("empty.csv", minSpreadFinder);

        Assertions.assertNull(minSpreadFinder.getMinimumDataPoint());
    }

    @Test
    void testCorruptCSV() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            MinSpreadFinder minSpreadFinder = new MinSpreadFinder();
            WeatherDataPointGenerator dataPointGenerator = new WeatherDataPointGenerator("weather_corrupt.csv", minSpreadFinder);
        });
    }

    @Test
    void runAll() {
        App.main();
    }

}