package com.example.jason.roulette;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        // Hide toolbar
        getSupportActionBar().hide();
        //Initialize YelpAPI
        new AsyncTask<Void, Void, Void>() {
            private IOException e;

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    System.out.println("Connecting to Yelp API");
                    YelpAPI.authenticate();
                } catch (IOException e){
                    this.e = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "Problem authenticating Yelp.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
                System.out.println("Connected to Yelp API");
            }
        }.execute();
    }

    public void showStock(View view) {
        Intent intent = new Intent(this, CardsActivity.class);
        SearchManager.clear();
        CardsActivity.clear();
        startActivity(intent);
    }

    public void showInstructions(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    public void showPlaces(View view) {
        //Intent intent = new Intent(this, PlacesActivity.class);
        //bahen: 43.6597348,-79.3996037
        //spadina: 43.6627297,-79.4047609
        String uri = "http://maps.google.com/";
        String uri2 = "http://maps.google.com/maps?saddr="
                + "43.6597348,-79.3996037" + "&daddr="
                + "43.6627297,-79.4047609";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri2));
        startActivity(intent);
    }

    public void showSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
