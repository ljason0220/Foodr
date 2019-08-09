package com.example.jason.roulette;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the YelpAPI on if it's loading values into it's results correctly
 * Since we're more interested in if a result is being added, we don't exhaustively test
 * the entire query's return. This is in addition to the fact that doing such would require
 * extensive hardcoding for the actual values.
 */

public class YelpAPITest {

    private static List<Result> results;
    private static double latitude;
    private static double longitude;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        List<Cuisine> cuisines = new ArrayList<Cuisine>();
        cuisines.add(Cuisine.INDPAK);
        latitude = 40.581140;
        longitude = -111.914184;
        //Otherwise this test would fail based on the time we ran it!
        YelpAPI.onlyIfOpen = "false";
        results = YelpAPI.callAPI(cuisines, "", latitude, longitude);
    }

    @Test
    public void createAndCheckResultsName() throws Exception {
        List<Result> expected = new ArrayList<Result>();
        Location location = new Location(-111.9218931, 40.5613466);
        Result res = new Result("India Palace", Cuisine.INDPAK, location);
        expected.add(res);
        System.out.println(expected.size() + " and actual has size " + results.size());
        //Check if we have the same name
        for (int i = 0; i < 1; i++) {
            assertEquals(expected.get(0).getName(), results.get(i).getName());
        }
    }

    @Test
    public void createAndCheckResultsCuisine() throws Exception {
        List<Result> expected = new ArrayList<Result>();
        Location location = new Location(-111.9218931, 40.5613466);
        Result res = new Result("India Palace", Cuisine.INDPAK, location);
        expected.add(res);
        //Check if we have the same cuisine
        for (int i = 0; i < 1; i++) {
            assertEquals(expected.get(0).getCuisine(), results.get(i).getCuisine());
        }
    }

    @Test
    public void createAndCheckResultsImage() {
        List<Image> expected = new ArrayList<>();
        //NOTE: Don't just use longitude and latitude since those are our search parameters, the returned
        //restaurants are unlikely to be at that exact location!
        Location location = new Location(-111.9218931, 40.5613466);
        Result res = new Result("India Palace", Cuisine.INDPAK, location);
        Image img = new Image("https://s3-media1.fl.yelpcdn.com/bphoto/mb-3A0v2zjhm_VlKZ5vtXg/o.jpg", res);
        res.addImage(img);
        expected.add(img);
        //Checks if YelpAPI correctly added an image to the Result.
        for (int i = 0; i < 1; i++) {
            //Test same URL
            assertEquals(expected.get(0).getUrl(), results.get(i).getImages().get(0).getUrl());
            //Test if same Result (Also check if our equals works for Result)
            assertEquals(expected.get(0).getResult().getName(), results.get(i).getName());
            assertEquals(expected.get(0).getResult().getCuisine(), results.get(i).getCuisine());
            assertEquals(expected.get(0).getResult().getLocation().getLongitude(), results.get(i).getLocation().getLongitude(), 0.001);
            assertEquals(expected.get(0).getResult().getLocation().getLatitude(), results.get(i).getLocation().getLatitude(), 0.001);
            assertEquals(expected.get(0).getResult().getLocation(), results.get(i).getLocation());
            assertEquals(expected.get(0).getResult(), results.get(i));
        }
    }

}
