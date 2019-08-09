package com.example.jason.roulette;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardsActivity extends AppCompatActivity {
    /** The list view */
    private RecyclerView recyclerView;
    /** The adapter for this list */
    private CardAdapter adapter;
    /** The list of images to manage */
    private static List<Image> images = SearchManager.getCuisineImages();  // Start with cuisine images
    /** The current state of execution. 0 = display cuisine images. 1 = display result images. 2 = display results. */
    private static int state = 0;
    /** The number of result images swiped */
    private static int swipeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        // Load cuisine images
        if (images.size() == 0) {
            images.addAll(SearchManager.getCuisineImages());
        }

        // Get RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);  // Improves performance

        // Set adapter
        adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);

        // Set layout manager
        recyclerView.setLayoutManager(new CardLayoutManager(this));

        // Set touch event handler
        ItemTouchHelper ith = new ItemTouchHelper(simpleCallback);
        ith.attachToRecyclerView(recyclerView);
    }

    /**
     * Used to clean up and reset the state whenever we begin a new search.
     */
    protected static void clear() {
        state = 0;
        images.clear();
        swipeCount = 0;
    }

    /**
     * @param image The image liked.
     */
    private void like(Image image) {
        image.getResult().incrementNetLikes();
        image.incrementLikes();
        System.out.println("Liked " + image);
        if (image.getUrl() == null) {  // Stock cuisine image
            search(image.getResult().getCuisine());
        } else {  // Result image
            swipeCount++;
        }
    }

    /**
     * @param image The image disliked.
     */
    private void dislike(Image image) {
        image.getResult().decrementNetLikes();
        image.decrementLikes();
        System.out.println("Disliked " + image);
        if (image.getUrl() != null) {  // Result image
            swipeCount++;
        }
    }

    /**
     * Conduct a search for a cuisine.
     */
    private void search(final Cuisine cuisine) {
        // Do search
        new AsyncTask<Void, Void, Void>() {
            private IOException e;

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    SearchManager.search(cuisine);
                } catch (IOException e) {
                    this.e = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (e != null) {
                    Toast.makeText(CardsActivity.this, "Problem retrieving results.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
                if (images.size() == 0) {
                    loadResultImages();
                }
            }
        }.execute();
    }

    private void loadResultImages() {
        int prevSize = images.size();
        if (prevSize < 3) {
            List<Image> resultImages = SearchManager.getResultImages(3 - prevSize);
            images.addAll(resultImages);
            System.out.println("Added " + resultImages.size() + " images");
            adapter.notifyItemRangeInserted(prevSize, resultImages.size());
            if (images.size() == 0) {
                Toast.makeText(CardsActivity.this, "No more results.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Show Result activity
     */
    private void showResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    /**
     * Adapter for cards.
     */
    private class CardAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.stock_card, parent, false);
            ViewHolder vh = new ViewHolder(cardView);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Image image = images.get(position);
            if (image.getUrl() == null) {  // Stock cuisine image
                Glide.with(CardsActivity.this)
                        .load(image.getResult().getCuisine().resourceId())
                        .into(holder.imageView);
            } else {  // Result image
                Glide.with(CardsActivity.this)
                        .load(image.getUrl())
                        .into(holder.imageView);
                System.out.println("Image url for " + image.getResult() + ": " + image.getUrl());
            }
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        /**
         * @param index The position of the image to return.
         * @return The image at the specified position.
         */
        public Image get(int index) {
            return images.get(index);
        }

        /**
         * @param index The position of the image to remove.
         */
        public void remove(int index) {
            // Remove image
            images.remove(index);
            notifyItemRemoved(index);
            if (swipeCount > 0 && (images.size() == 0 || swipeCount % 10 == 0)) {
                showResult();
            }
            loadResultImages();
        }
    }

    /**
     * Cache views for adapter.
     */
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            imageView = cardView.findViewById(R.id.foodItem);
            // Add button listeners
            cardView.findViewById(R.id.like).setOnClickListener(this);
            cardView.findViewById(R.id.dislike).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position >= 0 && position < adapter.getItemCount()) {
                Image image = adapter.get(position);
                if (v.getId() == R.id.like) {
                    like(image);
                } else {  // v.getId() == R.id.dislike
                    dislike(image);
                }
                adapter.remove(position);
            }
        }
    }

    /**
     * Layout manager for cards.
     */
    private class CardLayoutManager extends LinearLayoutManager {

        public CardLayoutManager(Context context) {
            super(context);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }

    /** The callback to handle touch events */
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.START | ItemTouchHelper.END) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (position >= 0 && position < adapter.getItemCount()) {
                Image image = adapter.get(position);
                if (direction == ItemTouchHelper.START) {
                    dislike(image);
                } else {  // direction == ItemTouchHelper.END
                    like(image);
                }
                adapter.remove(position);
            }
        }
    };
}
