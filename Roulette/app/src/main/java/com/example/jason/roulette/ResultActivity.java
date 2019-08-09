package com.example.jason.roulette;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    /** The restaurant result to display. */
    private Result result;
    /** The view to display restaurant image. */
    private HorizontalScrollView image;
    /** The view to display restaurant name */
    private TextView name;
    /** The view to display restaurant cuisine. */
    private TextView cuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_result);

        // Get the most liked result
        List<Result> results = SearchManager.getResults();
        Collections.sort(results, Collections.<Result>reverseOrder());
        result = results.get(0);

        // Find views to display Result
        image = findViewById(R.id.horscroll);
        name = findViewById(R.id.name);
        cuisine = findViewById(R.id.cuisine);

        // Display Result
        addImagesToTheGallery();
        displayResult();
    }

    /**
     * Display restaurant result.
     */
    private void displayResult() {
        name.setText(result.getName());
        cuisine.setText(result.getCuisine().toString());
    }

    private void addImagesToTheGallery() {
        LinearLayout gallery = (LinearLayout)findViewById(R.id.gallery);
        Integer count = 0;
        for (int i = 0; i < result.getImages().size(); i++) {
            gallery.addView(getImageView(result.getImages().get(i).getUrl()));
        }
    }

    private View getImageView(String url) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        imageView.setLayoutParams(lp);
        Glide.with(this)
                .load(url)
                .into(imageView);
        return imageView;
    }

    public void showNav(View view) {
        //Intent intent = new Intent(this, PlacesActivity.class);
        //bahen: 43.6597348,-79.3996037
        //System.out.println(SettingsActivity.getPriceRange());
        Location location = result.getLocation();
        double dlat = location.getLatitude();
        double dlon = location.getLongitude();
        Location current = SearchManager.getLocation();
        double clat = current.getLatitude();
        double clon = current.getLongitude();
        String uri = "http://maps.google.com/maps?saddr="
                + String.valueOf(clat) + "," + String.valueOf(clon) + "&daddr="
                + String.valueOf(dlat) + "," + String.valueOf(dlon);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
