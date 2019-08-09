package com.example.jason.roulette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A restaurant result.
 */
public class Result implements Comparable<Result> {
    /** The name of this restaurant. */
    private String name;
    /** The cuisine of this restaurant. */
    private Cuisine cuisine;
    /** The location of this restaurant. */
    private Location location;
    /** Images for this restaurant. */
    private List<Image> images;
    /* The placeID of this result*/
    private String placeID;
    /** Net number of times an image from this restaurant has been liked */
    private int netLikes;

    /**
     * Create a new restaurant result.
     *
     * @param name The name of this restaurant.
     * @param cuisine The cuisine of this restaurant.
     * @param location The location of this restaurant.
     */
    public Result(String name, Cuisine cuisine, Location location) {
        this.name = name;
        this.cuisine = cuisine;
        this.location = location;
        //New empty list
        this.images = new ArrayList<>();
        //Start off with no likes or dislikes
        this.netLikes = 0;
    }

    /**
     * @return The name of this restaurant.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The cuisine of this restaurant.
     */
    public Cuisine getCuisine() {
        return cuisine;
    }

    /**
     * @return The placeID of this restaurant
     */
    public String getPlaceID() { return placeID; }

    /**
     * Sets this restaurant's placeID to placeID
     * @param placeID
     */
    public void setPlaceID(String placeID) { this.placeID = placeID; }

    /**
     * @return The location of this restaurant.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return An unmodifiable view of the images for this restaurant.
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Adds Image img to the list of images that represents this restaurant
     * @param img the image to be added
     */
    public void addImage(Image img) {
        this.images.add(img);
        SearchManager.addResultImage(img);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Result) {
            Result r = (Result) o;
            return name.equals(r.getName()) && cuisine.equals(r.getCuisine());
        }
        return false;
    }

    /**
     * @return The net number of likes this restaurant has received
     */
    public int getNetLikes() { return netLikes; }

    /**
     * Increases netLikes by 1
     */
    public void incrementNetLikes() { netLikes += 1; }

    /**
     * Decreases netLikes by 1
     */
    public void decrementNetLikes() { netLikes -= 1; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Result o) {
        return Integer.compare(netLikes, o.netLikes);
    }
}
