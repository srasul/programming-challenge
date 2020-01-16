package de.exxcellent.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
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

        Assertions.assertThrows(RuntimeException.class, () ->{
            MinSpreadFinder footballMinSpreadFinder = new MinSpreadFinder();
            FootballDataPointGenerator footballDataPointGenerator = new FootballDataPointGenerator("1football.csv", footballMinSpreadFinder);
        });


    }

    @Test
    void testEmptyCSV() {

    }

    @Test
    void testCorruptCSV() {

    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

}