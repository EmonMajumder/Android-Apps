package com.example.movietrailerapp.moviecontent;

import android.provider.BaseColumns;

public final class MovieContract {

    public MovieContract()
    {

    }

    public static class movieTable implements BaseColumns
    {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_RATING = "rating";
    }
}
