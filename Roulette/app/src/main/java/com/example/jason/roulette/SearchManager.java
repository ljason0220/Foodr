package com.example.jason.roulette;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Stores search parameters and generates results.
 */
public class SearchManager {

    /** The cuisines to search for. */
    private static final List<Cuisine> cuisines = new ArrayList<>();
    /** The price range to search for. Represents number of dollar signs ($). */
    private static int priceRange = 2; //priceRange = SettingsActivity.getPriceRange();
    /** The group size to search for. */
    private static int groupSize = 1;
    /** The location to search around. */
    private static Location location = new Location(-79.397448, 43.659596);  // Toronto
    /** The radius to search around */
    private static int radius = 2000;
    /** The results of this search. */
    private static final List<Result> results = new ArrayList<>();
    /** The number of images to return from compileImages*/
    private static int numberResults = Cuisine.values().length;
    /** The result images of this search. */
    private static final List<Image> resultImages = new ArrayList<>();

    /**
     * Reset SearchManager to default search parameters.
     */
    protected static void clear() {
        cuisines.clear();
        groupSize = 1;
        location = new Location(-79.397448, 43.659596);
        results.clear();
        resultImages.clear();
    }

    /**
     * @param image The image to add to the SearchManager's result images.
     */
    public static void addResultImage(Image image) {
        resultImages.add(image);
    }

    /**
     * Add a cuisine to search for.
     *
     * @param cuisine The cuisine to search for.
     */
    public static void addCuisine(Cuisine cuisine) {
        cuisines.add(cuisine);
    }

    /**
     * Set the price range to search for. Represents number of dollar signs ($).
     * Default value is 1.
     *
     * @param priceRange The price range to search for.
     */
    public static void setPriceRange(int priceRange) {
        SearchManager.priceRange = priceRange;
    }

    /**
     * Set the group size to search for.
     * Default value is 1.
     *
     * @param groupSize The group size to search for.
     */
    public static void setGroupSize(int groupSize) {
        SearchManager.groupSize = groupSize;
    }

    /**
     * Set the location to search around.
     * Default location is Toronto.
     *
     * @param location The location to search around.
     */
    public static void setLocation(Location location) {
        SearchManager.location = location;
    }

    /**
     * Set the radius from Location to search around
     * Default radius is 400 meter or 0.25 miles
     *
     * @param radius The location to search around.
     */
    public static void setRadius(int radius) {
        SearchManager.radius = radius;
    }

    /**
     * Returns the list of cuisines
     *
     * @return  The list of cuisines
     */
    public static List<Cuisine> getCuisines() {
        return cuisines;
    }

    /**
     *
     * @return The price range
     */
    public static int getPriceRange() {
        return priceRange;
    }

    /**
     *
     * @return The groupsize
     */
    public static int getGroupSize() {
        return groupSize;
    }

    /**
     *
     * @return The location to search around
     */
    public static Location getLocation() {
        return location;
    }

    /**
     * Get the results and result images for this search.
     * @throws IOException If problem with Yelp API or Google Places API.
     */
    public static void search(Cuisine cuisine) throws IOException {
        System.out.println("Starting Yelp search for " + cuisine);
        List<Result> yelpResults = YelpAPI.callAPI(cuisine, Integer.toString(priceRange), location.getLatitude(), location.getLongitude(), radius);
        results.addAll(yelpResults);
        System.out.println("Finished Yelp search for " + cuisine);
        System.out.println("Starting Google places search for " + cuisine);
        PlacesAPI.callPlaceAPI(yelpResults);
        System.out.println("Finished Google places search for " + cuisine);
    }

    /**
     * @return The results of this search. Only valid after {@link #search}.
     */
    public static List<Result> getResults() {
        return results;
    }

    /**
     * @param n The max number of result images to return.
     * @return The result images of this search. Only valid after {@link #search}.
     */
    public static List<Image> getResultImages(int n) {
        List<Image> images = new ArrayList<>();
        Collections.shuffle(resultImages);
        for (int i = 0; i < Math.min(n, resultImages.size()); i++) {
            images.add(resultImages.remove(0));
        }
        return images;
    }

    /**
     * @return The stock cuisine images.
     */
    public static List<Image> getCuisineImages() {
        List<Image> images = new ArrayList<>();
        for (Cuisine cuisine : Cuisine.values()) {
            Result result = new Result(null, cuisine, null);
            Image image = new Image(null, result);
            images.add(image);
        }
        return images;
    }

    /**
     * From all of our results, compile a list of images and organize them
     * Currently we use a random algorithm
     * TODO: This code looks horrible, all those for loops
     * @return a compiled list of images to be displayed
     */
    private static List<Image> compileImages() {
        //Return images as equally as possible to the number of different cuisines
        List<Image> images = new ArrayList<>();
        int cuisineCount = cuisines.size();

        if (cuisineCount == 0) {
            //Return empty list
            return images;
        } else if (cuisineCount == 1) {
            //Intermediate to shuffle all before filtering taking top cards
            List<Image> allRandom = new ArrayList<>();
            //If we only have 1 cuisine or we have too many, we'll just default to randomizing all
            for (int i = 0; i < results.size(); i++){
                allRandom.addAll(results.get(i).getImages());
            }

            Collections.shuffle(allRandom);
            //Add up to numberResults
            int count = Math.min(numberResults, allRandom.size());
            for (int i = 0; i < count; i++) {
                images.add(allRandom.get(i));
            }
        } else {
            //For each cuisine, compile all results of that cusine together. Shuffle and add share to images.
            int portion = numberResults / cuisineCount; //Integer division
            int[] extraShares = new int[numberResults];
            //Initialize with 0's
            for (int i = 0; i < numberResults; i++) {
                extraShares[i] = 0;
            }
            //If not factor, we won't have equal shares, randomly determine who gets more shares
            if (numberResults % cuisineCount > 0) {
                int cardsLeft = numberResults - portion*cuisineCount;
                Random random = new Random();
                for (int i = 0; i < cardsLeft; i++) {
                    //Possible for one cuisine to hog all the extra shares
                    extraShares[random.nextInt(numberResults)] += 1;
                }
            }
            //For each cuisine, find their images, randomize, and then get add theur portion
            //and extra shares, if any to our final result
            for (int j = 0; j < cuisineCount; j++) {
                List<Image> cuisineImages = findCusineImages(cuisines.get(j));
                Collections.shuffle(cuisineImages);
                int shares = portion + extraShares[j];
                //System.out.println(cuisines.get(j) + " share is " + shares);
                //Add first share entries into images
                //TODO: Find way to handle a cuisine not having enough images! Should we reasign?
                //Check if we have enough images, dump shares if we don't have enough images
                shares = Math.min(shares, cuisineImages.size());
                //System.out.println(cuisines.get(j) + " share is updated to " + shares);
                for (int i = 0; i < shares; i++) {
                    images.add(cuisineImages.get(i));
                }
            }
        }
        return images;
    }

    /**
     * Given a cuisine, we find all images of a restaurant that serves that kind of cuisine
     * @param cuisine the cuisine type to search images for
     * @return the list of images for the given cuisine
     */
    private static List<Image> findCusineImages(Cuisine cuisine) {
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getCuisine().equals(cuisine)) {
                //Add to our list
                images.addAll(results.get(i).getImages());
            }
        }
        return images;
    }

    /**
     * Helper function to print all images
     * @param images the images to print
     */
    private void printImages(List<Image> images) {
        for (int i = 0; i < images.size(); i++) {
            System.out.println(images.get(i));
        }
    }
}
