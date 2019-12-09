package com.example.movietrailerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movietrailerapp.moviecontent.MovieDbHelper;
import com.example.movietrailerapp.moviecontent.MovieItem;

public class AddMovieTrailer extends AppCompatActivity{

    private String name;
    private String thumbnail;
    private String description;
    private String link;
    private int rating;
    private Button save;
    private Button back;
    private EditText EditName;
    private EditText EditDescription;
    private EditText EditLink;
    private EditText EditRating;


    MovieDbHelper mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mdb = new MovieDbHelper(this);
        final Intent intent = getIntent();
        String v = intent.getStringExtra("key");
        setContentView(R.layout.add_movie);

        EditName = findViewById(R.id.txtEditName);
        EditDescription = findViewById(R.id.txtEditdescription);
        EditLink = findViewById(R.id.txtEditLink);
        EditRating = findViewById(R.id.txtEditrating);

        save = findViewById(R.id.buttonsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EditName.getText().toString();
                String thumbnail = "";
                String description = EditDescription.getText().toString();
                String link = EditLink.getText().toString();
                int rating = Integer.parseInt(EditRating.getText().toString());
                addMovieItem(name,description,link,rating);
                setResult(RESULT_OK,new Intent());
                finish();
            }
        });
    }

    private void addMovieItem(String name, String description,String link,int rating)
    {
        MovieItem movieitem;
        movieitem = new MovieItem(0,name,"",description,link,rating);
        mdb.addMovie(movieitem);
    }
}
