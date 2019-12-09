package com.example.movietrailerapp.moviecontent;

/**
 * A movie item representing a piece of content.
 */
public class MovieItem {
    public int id;
    public String name;
    public String thumbnail;
    public String description;
    public String link;
    public int rating;

    public MovieItem()
    {
        this.id = 0;
        this.name = null;
        this.thumbnail = null;
        this.description = null;
        this.link = null;
        this.rating = 0;
    }

    public MovieItem(int id, String name, String thumbnail, String description, String link, int rating ) {
        this.id = id;
        this.name = name;
        this.thumbnail = "";
        this.description = description;
        this.link = link;
        this.rating = rating;
    }
}
