package com.example.jason.roulette;

import com.google.maps.NearbySearchRequest;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import com.google.maps.GeoApiContext;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.RankBy;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.maps.PlacesApi.nearbySearchQuery;
import static java.lang.Math.min;

/**
 * Using https://github.com/googlemaps/google-maps-services-java for Places Web Service
 * Uses the Place API to try to get Place Photos
 * TODO: We use up so much google API queries!
 */

public class PlacesAPI {
    /** Secure storage of API key */
    private static String key = "ZZZZZZZZZZZZZZZZZZZZZZ";
    /** Global variable used for testing */
    public static boolean onlyOpen = false;

    public static GeoApiContext context;

    /* TODO: Sets to user's phone? */
    /** The maxHeight of the photos we get */
    public static int maxHeight = 640;
    /** The maxWidth of the photos we get */
    public static int maxWidth = 360;

    public static void callPlaceAPI(List<Result> results) {
        try {
            //TODO: Chain call parameters
            //Add key
            GeoApiContext.Builder builder = new GeoApiContext.Builder().apiKey(key);
            //Add extra stuff to builder
            //Never timeout!
            //builder.connectTimeout(0, TimeUnit.SECONDS);
            //builder.readTimeout(0, TimeUnit.SECONDS);
            //builder.writeTimeout(0, TimeUnit.SECONDS);
            //Default value
            //builder.retryTimeout(60, TimeUnit.SECONDS);
            //Can retry twice at most?
            //builder.maxRetries(2);
            //builder.setIfExceptionIsAllowedToRetry(ApiException(), true);
            builder.disableRetries();
            context = builder.build();
            //GeoApiContext context = builder.build();

            //For each result, generate a new searchrequest and query the nearby location
            for (Result result : results) {
                //Get our request object with Result's location
                LatLng location = new LatLng();
                location.lat = result.getLocation().getLatitude();
                location.lng = result.getLocation().getLongitude();
                //Note: NearbySearchRequest are not reusable! We have to generate a new one for
                //each result!
                NearbySearchRequest request = nearbySearchQuery(context, location);

                //Fill in request with global query stuff
                request.openNow(onlyOpen);
                request.type(PlaceType.RESTAURANT);
                //If we want another algorithm we can check the documentation pages
                request.rankby(RankBy.DISTANCE);
                search(request, result);
            }
        } catch (Exception e) {
            //Mainly to catch any OverDailyLimitException
            e.printStackTrace();
        }
    }

    /**
     * Searches using a NearbySearchRequest and finds Place Photos if there is a match.
     * @param request the set up NearbySearchRequest
     * @param result the result we are searching for
     */
    private static void search(NearbySearchRequest request, Result result) {
        //Fill in with data
        request.name(result.getName());
        try {
            System.out.println("Search Google Nearby Places around location of " + result);
            //Wait until our result is done!
            PlacesSearchResponse response = request.await();
            //The google results found
            PlacesSearchResult results[] = response.results;
            System.out.println("Received " + results.length + " results around location of " + result);
            //Check to see if our Result is amongst these, if it is, find photos and add
            //For optimization issues, only check first three
            for (int i = 0; i < min(2, results.length); i++) {
                PlacesSearchResult res = results[i];
                //if (nameCompare(res.name, result.getName())) {
                if (similarity(result.getName(), res.name) >= 0.5) {
                    result.setPlaceID(res.placeId);
                    //Get photos
                    //System.out.println("DEBUG: Adding photos!");
                    for (Photo photo : res.photos) {
                        //USe photoreference to get a photo url
                        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + maxWidth + "&maxheight="
                                + maxHeight + "&photoreference=" + photo.photoReference + "&key=" + key;
                        System.out.println("Image url for " + result + ": " + url);
                        Image img = new Image(url, result);
                        result.addImage(img);
                    }
                    System.out.println("Result " + result + " now has " + result.getImages().size() + " photos.");

                    System.out.println("Search Google Places Details for " + result);
                    //Call Places Detail Search for up to 10 extra photos!
                    PlaceDetailsRequest detailRequest = new PlaceDetailsRequest(context);
                    detailRequest.placeId(res.placeId);
                    PlaceDetails details = detailRequest.await();
                    System.out.println("Received " + details.photos.length + " images for " + result);
                    for (Photo photo : details.photos) {
                        //USe photoreference to get a photo url
                        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + maxWidth + "&maxheight="
                                + maxHeight + "&photoreference=" + photo.photoReference + "&key=" + key;
                        System.out.println("Image url for " + result + ": " + url);
                        Image img = new Image(url, result);
                        result.addImage(img);
                    }
                    System.out.println("Result " + result + " now has " + result.getImages().size() + " photos.");
                    //System.out.println("Finished details request, restaurant with name " + result.getName() + " now has " + result.getImages().size() + " photos.");

                    //Found all the photos for this restaurant! Exit early
                    break;
                }
            }
            //System.out.println("DEBUG: Finished comparing results for " + result.getName());
        } catch (IOException e1) {
            System.out.println(e1);
        } catch (InterruptedException e2) {
            System.out.println(e2);
        } catch (ApiException e3) {
            System.out.println(e3);
        }
    }

    /**
     * TODO: Perhaps a non-exact match could be used?
     * TODO: Right now Harvey's (Google) and Harvey's Restaurants (Yelp) are not the same!
     * @param name1 The first string to be compared
     * @param name2 The second string to be compared
     * @return
     */
    private static boolean nameCompare(String name1, String name2) {
        System.out.println("Trying to compare " + name1 + " and " + name2);
        if (name1.equals(name2)) {
            return true;
        }
        return false;
    }

    /**
     * Computes the Jaccard similarity of two strings.
     *
     * The Jaccard similarity is a measure used for comparing the similarity of sets.
     * The Jaccard similarity of two strings is defined on the set of tokens of the strings.
     * It is the size of the intersection divided by the size of the union of the token sets.
     *
     * @param  a  the first string
     * @param  b  the second string
     * @return    the Jaccard similarity (between 0.0 and 1.0 inclusively) of a and b
     */
    protected static double similarity(String a, String b) {
        a = Normalizer.normalize(a, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        b = Normalizer.normalize(b, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        final java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\s+");
        Set<String> left = new HashSet<>(Arrays.asList(p.split(a)));
        Set<String> right = new HashSet<>(Arrays.asList(p.split(b)));
        final int sa = left.size();
        final int sb = right.size();
        if ((sa - 1 | sb - 1) < 0)
            return 0.0;
        if ((sa + 1 & sb + 1) < 0)
            return 0.0;
        final Set<?> smaller = sa <= sb ? left : right;
        final Set<?> larger  = sa <= sb ? right : left;
        int intersection = 0;
        for (final Object element : smaller) try {
            if (larger.contains(element))
                intersection++;
        } catch (final ClassCastException | NullPointerException e) {}
        final long sum = (sa + 1 > 0 ? sa : left.size())
                + (sb + 1 > 0 ? sb : right.size());
        System.out.println("Yelp name " + a + " and Google Places name " + b + " are " + 1d / (sum - intersection) * intersection + " similar");
        return 1d / (sum - intersection) * intersection;
    }

}
