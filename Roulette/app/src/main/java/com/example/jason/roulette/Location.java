package com.example.jason.roulette;

/**
 * Stores the location data of a Result
 * TODO: Currently only stores longitude and latitude, add other stuff if needed (such as street address?)
 */

public class Location {
    /* The longitude of this location */
    private double longitude;
    /* The latitude of this location */
    private double latitude;

    /**
     * Creates a new location
     * @param longitude the longitude of this location
     * @param latitude the latitude of this location
     */
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * @return The longitude of this location
     */
    public double getLongitude(){
        return this.longitude;
    }

    /**
     * @return The latitude of this location
     */
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Location && ((Location) object).longitude == longitude
                && ((Location) object).latitude == latitude) {
            return true;
        }
        return false;
    }
}
