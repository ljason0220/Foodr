package com.example.jason.roulette;

import android.graphics.Bitmap;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests creating a SearchManager and tests calling YelpAPI
 */

public class SearchManagerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //Otherwise this test would fail based on the time we ran it!
        YelpAPI.onlyIfOpen = "false";
    }

    @Test
    public void createAndCheckDefaultSearchManager() {
        SearchManager searchManager = SearchManager.beginSearch();
        List<Cuisine> expectedCuisines = new ArrayList<>();
        int expectedPrice = 1;
        int expectedGroup = 1;
        assertEquals(expectedCuisines, searchManager.getCuisines());
        assertEquals(expectedPrice, searchManager.getPriceRange());
        assertEquals(expectedGroup, searchManager.getGroupSize());
        assertNull(searchManager.getLocation());
    }

    @Test
    public void setSearchManagerAndCheck() {
        SearchManager searchManager = SearchManager.beginSearch();
        List<Cuisine> expectedCuisines = new ArrayList<>();
        expectedCuisines.add(Cuisine.INDPAK);
        int expectedPrice = 2;
        int expectedGroup = 2;
        Location expectedLocation = new Location(-111.914184,40.581140);

        searchManager.setGroupSize(2);
        searchManager.addCuisine(Cuisine.INDPAK);
        Location location = new Location(-111.914184,40.581140);
        searchManager.setLocation(location);
        searchManager.setPriceRange(2);

        assertEquals(expectedCuisines, searchManager.getCuisines());
        assertEquals(expectedPrice, searchManager.getPriceRange());
        assertEquals(expectedGroup, searchManager.getGroupSize());
        assertEquals(expectedLocation, searchManager.getLocation());
    }

    @Test
    public void SetSearchManagerAndcallAPI() {
        SearchManager searchManager = SearchManager.beginSearch();
        searchManager.addCuisine(Cuisine.INDPAK);
        Location location = new Location(-111.914184,40.581140);
        searchManager.setLocation(location);
        //NOTE: We can't actually set our price range to "" for callAPI()

        List<Result> expectedResults = new ArrayList<>();
        Location resultlocation = new Location(-111.8548467, 40.5951649);
        Result result = new Result("Bhutan House Restaurant", Cuisine.INDPAK, resultlocation);
        Image image = new Image("https://s3-media2.fl.yelpcdn.com/bphoto/suIrMDFhcbf54GrMziBBYA/o.jpg", result);
        result.addImage(image);
        expectedResults.add(result);

        List<Result> actualResults = searchManager.getResults();
        for (int i = 0; i < 1; i++) {
            assertEquals(expectedResults.get(i).getName(), actualResults.get(i).getName());
            assertEquals(expectedResults.get(i).getLocation().getLatitude(), actualResults.get(i).getLocation().getLatitude(), 0.001);
            assertEquals(expectedResults.get(i).getLocation().getLongitude(), actualResults.get(i).getLocation().getLongitude(), 0.001);
            assertEquals(expectedResults.get(i).getImages().get(0).getUrl(), actualResults.get(i).getImages().get(0).getUrl());
            assertEquals(expectedResults.get(i), actualResults.get(i));
            assertEquals(expectedResults.get(i), searchManager.getResults().get(i));
        }
    }

    /**
     * Check if the compiled images are actually in a different order
     * Not a good test, there is a small possibility that randomly shuffling will result in same
     * as unmodified.
     */
    @Test
    public void TestCompileImages() {
        SearchManager searchManager = SearchManager.beginSearch();
        searchManager.addCuisine(Cuisine.INDPAK);
        Location location = new Location(-111.914184,40.581140);
        searchManager.setLocation(location);

        List<Result> actualResults = new ArrayList<>();
        //Make a deep copy
        for (int i = 0; i < searchManager.getResults().size(); i++) {
            actualResults.add(searchManager.getResults().get(i));
        }
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < actualResults.size(); i++) {
            images.addAll(actualResults.get(i).getImages());
        }

        List<Image> shuffledImages = searchManager.compileImages();
        //for (int i = 0; i < images.size(); i++) {
        //    assertEquals(images.get(i), shuffledImages.get(i));
        //}

        //DEBUG output
        //System.out.println("Printing unmodified list");
        //searchManager.printImages(images);
        //System.out.println("Printing shuffled list");
        //searchManager.printImages(shuffledImages);

        assertNotEquals(images, shuffledImages);
    }

    /**
     * Test if we're allocating equal shares
     */
    @Test
    public void TestCompileImagesShares() {
        SearchManager searchManager = SearchManager.beginSearch();
        searchManager.addCuisine(Cuisine.INDPAK);
        searchManager.addCuisine(Cuisine.CHINESE);
        Location location = new Location(-111.914184,40.581140);
        searchManager.setLocation(location);
        int expectedShare1 = 5;
        int expectedShare2 = 5;
        int share1 = 0;
        int share2 = 0;

        List<Result> actualResults = new ArrayList<>();
        //Make a deep copy to see if there's actually a shuffling
        for (int i = 0; i < searchManager.getResults().size(); i++) {
            actualResults.add(searchManager.getResults().get(i));
        }
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < actualResults.size(); i++) {
            images.addAll(actualResults.get(i).getImages());
        }

        List<Image> shuffledImages = searchManager.compileImages();
        //Compile actual shares
        for (int i = 0; i < shuffledImages.size(); i++) {
            Cuisine cuisine = shuffledImages.get(i).getResult().getCuisine();
            if (cuisine.equals(Cuisine.INDPAK)) {
                share1 += 1;
            } else if (cuisine.equals(Cuisine.CHINESE)) {
                share2 += 1;
            }
        }

        //DEBUG: print statements
        //System.out.println("Printing unmodified list");
        //searchManager.printImages(images);
        //System.out.println("Printing shuffled list");
        //searchManager.printImages(shuffledImages);

        assertEquals(expectedShare1, share1);
        assertEquals(expectedShare2, share2);
    }
}
