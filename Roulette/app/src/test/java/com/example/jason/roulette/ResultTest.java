package com.example.jason.roulette;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests if we can properly create a result
 */

public class ResultTest {

    private static Result result;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Location location = new Location(79.3832, 43.6532);
        result = new Result("Hello", Cuisine.BRITISH, location);
    }

    @Test
    public void createAndCheckName() throws Exception {
        assertEquals("Hello", result.getName());
    }

    @Test
    public void createAndCheckCuisine() throws Exception {
        assertEquals(Cuisine.BRITISH, result.getCuisine());
    }

    @Test
    public void createAndCheckLocation() throws Exception {
        Location expected = new Location(79.3832, 43.6532);
        assertEquals(expected, result.getLocation());
    }
}
