package com.example.jason.roulette;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Place API
 */

public class PlacesAPITest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //Makes our tests consistent regardless of time of day
        PlacesAPI.onlyOpen = false;
        YelpAPI.onlyIfOpen = "false";
    }

    /**
     * Tests setting up a SearchManager and calling the Places API
     */
    @Test
    public void SetSearchManagerAndcallPlacesAPI() {
        SearchManager searchManager = SearchManager.beginSearch();
        /*for (Cuisine cuisine : Cuisine.values()) {
            searchManager.addCuisine(cuisine);
        }*/
        searchManager.addCuisine(Cuisine.INDPAK);
        searchManager.addCuisine(Cuisine.CHINESE);
        searchManager.addCuisine(Cuisine.TRADAMERICAN);

        //Somewhere
        //double lat = 40.581140;
        //double lon = -111.914184;

        //Toronto
        double lat = 43.6532;
        double lon = -79.3832;
        Location location = new Location(lon,lat);
        searchManager.setLocation(location);

        List<Result> expectedResults = new ArrayList<>();
        Location resultlocation = new Location(lon, lat);

        //TODO: Properly write a unit test, this is dummy output to get something to show up.
        Result result = new Result("Bhutan House Restaurant", Cuisine.INDPAK, resultlocation);
        Image image = new Image("https://maps.googleapis.com/maps/api/place/photo?maxwidth=360&maxheight=640&photoreference=CmRaAAAAF7GsQP1jXKPisOyVk7hIglUfvVJSj6CBrAiaf59MPzQp-8jwZNA8c2a6naKkYIsRLwU77jdPRIYcF2afhExWTjFLM9PsKJs_7NTyIBUCCiwPsXZZBIBvB9LizinHRqQcEhCroMAjE5tF6s2EdJhyJDuZGhTNs4Peoyx8hsnWvxBCqy4Q7FYnRg&key=ZZZZZZZZZZZZZZZZZZZZZZZZ", result);
        result.addImage(image);
        expectedResults.add(result);

        //Calling YelpAPI
        List<Result> actualResults = searchManager.getResults();

        //Calling PlacesAPI
        PlacesAPI.callPlaceAPI(searchManager);

        //Get first entries
        Result resExpected = expectedResults.get(0);
        Result resActual = actualResults.get(0);

        System.out.println("Size of actual is " + actualResults.size());

        //Count how many images we have
        int counter = 0;
        //Display all our image links
        for (int i = 0; i < actualResults.size(); i++) {
            searchManager.printImages(actualResults.get(i).getImages());
            counter += actualResults.get(i).getImages().size();
        }
        System.out.println("In total images found were: " + counter);

        for (int i = 0; i < resExpected.getImages().size(); i++) {
            System.out.println("Image size is  " + resExpected.getImages().size());
            assertEquals(resExpected.getName(), resActual.getName());
            assertEquals(resExpected.getLocation().getLatitude(), resActual.getLocation().getLatitude(), 0.001);
            assertEquals(resExpected.getLocation().getLongitude(), resActual.getLocation().getLongitude(), 0.001);
            assertEquals(resExpected.getImages().get(i).getUrl(), resActual.getImages().get(0).getUrl());
            assertEquals(resExpected, actualResults.get(i));
            assertEquals(resExpected, searchManager.getResults().get(i));
        }
    }
}
