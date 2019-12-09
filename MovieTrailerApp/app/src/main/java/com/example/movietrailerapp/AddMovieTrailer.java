package com.example.movietrailerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movietrailerapp.moviecontent.MovieDbHelper;
import com.example.movietrailerapp.moviecontent.MovieItem;

public class AddMovieTrailer extends AppCompatActivity{

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

        back = findViewById(R.id.buttonback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK,new Intent());
                finish();
            }
        });

        save = findViewById(R.id.buttonsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    String name = EditName.getText().toString();
                    String description = EditDescription.getText().toString();
                    String link = EditLink.getText().toString();
                    String ratingstr = EditRating.getText().toString();
                    int rating = 0;
                    int errorcount = 0;

                    if(name.isEmpty())
                    {
                        Toast.makeText(AddMovieTrailer.this,"Please give a input for name",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    else if(!name.matches("^\\S+(\\s\\S+)*$"))
                    {
                        Toast.makeText(AddMovieTrailer.this,"Invalid input for name",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }

                    if(description.isEmpty())
                    {
                        Toast.makeText(AddMovieTrailer.this,"Please give a input for description",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    else if(!description.matches("^\\S+(\\s\\S+)*$"))
                    {
                        Toast.makeText(AddMovieTrailer.this,"Invalid input for name",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    if(link.isEmpty())
                    {
                        Toast.makeText(AddMovieTrailer.this,"Please give a input for link",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    else if(!link.matches("^\\S+(\\s\\S+)*$"))
                    {
                        Toast.makeText(AddMovieTrailer.this,"Invalid input for name",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    if(ratingstr.isEmpty())
                    {
                        Toast.makeText(AddMovieTrailer.this,"Please give a input for rating",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    else if(!ratingstr.matches("^\\d+$"))
                    {
                        Toast.makeText(AddMovieTrailer.this,"Invalid input for rating",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    else if(Integer.parseInt(ratingstr)>10 || Integer.parseInt(ratingstr)<0){
                        Toast.makeText(AddMovieTrailer.this,"Rating should be between 0 to 10",Toast.LENGTH_LONG).show();
                        errorcount++;
                    }
                    else
                    {
                        rating = Integer.parseInt(ratingstr);
                    }

                    if(errorcount == 0)
                    {
                        addMovieItem(name,description,link,rating);
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(AddMovieTrailer.this,"Invalid input for Name.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addMovieItem(String name, String description,String link,int rating)
    {
        MovieItem movieitem;
        movieitem = new MovieItem(0,name,"",description,link,rating);
        mdb.addMovie(movieitem);
        EditName.setText("");
        EditDescription.setText("");
        EditLink.setText("");
        EditRating.setText("");
    }
}
