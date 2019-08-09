package com.example.jason.roulette;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SettingsActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener
{
    private static int priceRange = 1;
    private int distance = 1;
    private Spinner priceSpinner;
    private Spinner distanceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        // Spinner element
        priceSpinner = (Spinner) findViewById(R.id.price_spinner);
        distanceSpinner = (Spinner) findViewById(R.id.distance_spinner);

        // Make the second option the default
        priceSpinner.setSelection(1);

        // Spinner click listener
        priceSpinner.setOnItemSelectedListener(this);
        distanceSpinner.setOnItemSelectedListener(this);
    }

    @Override
     public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String item = parent.getItemAtPosition(pos).toString();
        System.out.println(parent.getId());
        System.out.println(R.id.price_spinner);
        System.out.println(R.id.distance_spinner);
        if (parent.getId() == R.id.price_spinner)
        {
            if (item.equals("$"))
            {
                priceRange = 1;
                //Toast.makeText(SettingsActivity.this, "Cheap", Toast.LENGTH_SHORT).show();
            }
            else if (item.equals("$$"))
            {
                priceRange = 2;
            }
            else if (item.equals("$$$"))
            {
                priceRange = 3;
            }
            else
            {
                priceRange = 4;
                //Toast.makeText(SettingsActivity.this, "Expensive", Toast.LENGTH_SHORT).show();
            }
            SearchManager.setPriceRange(priceRange);
            System.out.println(priceRange);
        }
        else //if (parent.getId() == R.id.distance_spinner)
        {
            if (item.equals("Under 2km"))
            {
                distance = 2000;
                //Toast.makeText(SettingsActivity.this, "Close", Toast.LENGTH_SHORT).show();
            }
            else if (item.equals("Under 4km"))
            {
                distance = 4000;
            }
            else
            {
                distance = 8000;
                //Toast.makeText(SettingsActivity.this, "Expensive", Toast.LENGTH_SHORT).show();
            }
            System.out.println(distance);
            SearchManager.setRadius(distance);
        }
    }
    public void onNothingSelected(AdapterView<?> parent)
    {
        // Do nothing for now
    }

    /**
     * @return The desired price range
     */
    public static int getPriceRange() { return priceRange; }

    /**
     * @return The desired distance range
     */
    public int getDistance() { return distance; }

}
