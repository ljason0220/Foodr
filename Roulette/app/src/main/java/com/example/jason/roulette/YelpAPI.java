package com.example.jason.roulette;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Accesses Yelp's API for use with app.
 * Specifically, we can generate a list of Results from a set of Yelp queries' outputs
 */

public class YelpAPI {

    /* Very safe storage of app credentials*/
    private static String appId = "-iLInlrnASpipVpOvsxteg";
    private static String appSecret = "AZDgpljKtImKZiIoUmOOBs0l6B2wIRLttiFcpuURV0iNhXLGwKp9IJCWF6uAvqpE";
    /* For testing */
    public static String onlyIfOpen = "false";

    private static YelpFusionApi yelpFusionApi;

    public static void authenticate() throws IOException {
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            yelpFusionApi = apiFactory.createAPI(appId, appSecret);
    }

    /**
     * Fetches results from Yelp using specified search parameters.
     *
     * @param cuisine
     * @param price
     * @param latitude
     * @param longitude
     * @return
     */
    public static List<Result> callAPI(
            Cuisine cuisine, String price, double latitude, double longitude, int radius) throws IOException {
        List<Result> results = new ArrayList<>();
        // Establish connection to Yelp
        //YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
        //YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);
        //If we didn't crash, we now have a working connection

        // Build params dictionary
        // Look here for what kind of things we can put
        // https://www.yelp.ca/developers/documentation/v3/business_search
        Map<String, String> params = new HashMap<>();
        params.put("price", price);
        params.put("latitude", Double.toString(latitude));
        params.put("longitude", Double.toString(longitude));
        params.put("open_now", onlyIfOpen);
        params.put("radius", Integer.toString(radius));  // Hardcode to only return businesses open now
        //Reduce number of results we want back.
        params.put("limit", "5");
        // Have to do a search for each cuisine
//        for (Cuisine cuisine : cuisines) {
            params.put("categories", cuisine.name().toLowerCase());
            // TODO: Remove testing println
            System.out.println("Search Yelp for: " + params.toString());

            // Request data from Yelp
            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
            Response<SearchResponse> response = call.execute();
            //If we didn't crash, we now have a valid response

            //Parse response
            SearchResponse searchResponse = response.body();
            ArrayList<Business> businesses = searchResponse.getBusinesses();

            //Add stuff to results
            System.out.println("Received " + businesses.size() + " results for " + cuisine);
            for (Business business : businesses) {
                // Get location data
                Location location = new Location(business.getCoordinates().getLongitude(), business.getCoordinates().getLatitude());
                // Build result
                Result result = new Result(business.getName(), cuisine, location);

                // Avoid adding duplicate chain restaurants
                if (results.contains(result)) {
                    System.out.println("Skipped adding result " + result);
                    continue;
                }

                //For now have one image from the Yelp API itself
                System.out.println("Image url for " + result + ": " + business.getImageUrl());
                if (!business.getImageUrl().isEmpty()) {
                    result.addImage(new Image(business.getImageUrl(), result));
                }

                //Try to get extra photos, can have up to three extra.
                List<String> businessPhotos =  business.getPhotos();
                if (businessPhotos!= null) {
                    for (String url : businessPhotos) {
                        System.out.println("Image url for " + result + ": " + url);
                        result.addImage(new Image(url, result));
                    }
                }
                System.out.println("Adding result " + result + " with " + result.getImages().size() + " photos");
                results.add(result);
            }
//        }
        return results;
    }
}
