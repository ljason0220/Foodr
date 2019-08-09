package com.example.jason.roulette;

/**
 * Represents an image
 * Stores the result that it represents
 */

public class Image {
    private String url;
    private Result result;
    private int likes;

    /**
     * Creates a new image that is from the restaurant result
     * @param url the url of the image
     * @param result the Result we are an image of
     */
    public Image(String url, Result result){
        this.url = url;
        this.result = result;
        likes = 0;
    }

    /**
     * @return the url of the image
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * @return the restaurant we are an image of
     */
    public  Result getResult(){
        return this.result;
    }

    @Override
    public String toString() {
        if (url == null) {  // Cuisine image
            return result.getCuisine().toString();
        } else {  // Result image
            return result.getName();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Image) {
            Image i = (Image) o;
            return url.equals(i.getUrl());
        }
        return false;
    }

    public void incrementLikes ()
    {
        likes += 1;
    }

    public void decrementLikes ()
    {
        likes -= 1;
    }
}
