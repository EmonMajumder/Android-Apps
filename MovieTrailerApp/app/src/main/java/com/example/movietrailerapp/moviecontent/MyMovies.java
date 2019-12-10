package com.example.movietrailerapp.moviecontent;
import com.example.movietrailerapp.ItemListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMovies {
    /**
     * An array of movie type items.
     */
    public static List<MovieItem> ITEMS = new ArrayList<>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, MovieItem> ITEM_MAP = new HashMap<>();

    private static int COUNT = ItemListActivity.allMoviesList.size();

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(ItemListActivity.allMoviesList.get(i));
        }
    }

    private static void addItem(MovieItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }
}
