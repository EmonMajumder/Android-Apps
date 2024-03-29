package com.example.movietrailerapp;
import android.content.Intent;
import android.os.Bundle;
import com.example.movietrailerapp.moviecontent.MovieDbHelper;
import com.example.movietrailerapp.moviecontent.MovieItem;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayerFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

/** An activity representing a list of Items.*/
public class ItemDetailActivity extends AppCompatActivity {

    public MovieDbHelper moviedbhelper = new MovieDbHelper(this);
    public static final String ARG_ITEM_ID = "item_id";

    Button play;
    Button delete;
    Button submit;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            play = findViewById(R.id.btnplay);
            play.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view){
                    String id = getIntent().getStringExtra(ItemDetailActivity.ARG_ITEM_ID);

                    try
                    {
                        String link = moviedbhelper.movielink(id);
                        Intent intent = new Intent(ItemDetailActivity.this, VideoPlayer.class);
                        intent.putExtra("vlink", link);
                        startActivity(intent);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(ItemDetailActivity.this,"Video Unavailable",Toast.LENGTH_LONG).show();
                    }
                }
            });

            ratingBar = findViewById(R.id.ratingBar);
            submit = findViewById(R.id.buttonsubmit);
            submit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view){

                    try
                    {
                        float rate = 2*ratingBar.getRating();
                        int rateint = (int)rate;
                        String id = getIntent().getStringExtra(ItemDetailActivity.ARG_ITEM_ID);
                        if(moviedbhelper.updaterating(id,rateint))
                        {
                            Toast.makeText(ItemDetailActivity.this,"Your rating was submitted.",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ItemDetailActivity.this,"Error submitting your rating.",Toast.LENGTH_LONG).show();
                        }

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(ItemDetailActivity.this,"Error submitting your rating.",Toast.LENGTH_LONG).show();
                    }
                }
            });

            delete = findViewById(R.id.buttondelete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int result;
                    try
                    {
                        String id = getIntent().getStringExtra(ItemDetailActivity.ARG_ITEM_ID);

                        result = moviedbhelper.deleteMovie(Integer.parseInt(id));

                        if(result>0)
                        {
                            Toast.makeText(ItemDetailActivity.this,"Record deleted from database.",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ItemDetailActivity.this,"Record could not be deleted from database.",Toast.LENGTH_LONG).show();
                        }
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(ItemDetailActivity.this,"Record does not exist.",Toast.LENGTH_LONG).show();
                    }

                    Intent intent = new Intent(ItemDetailActivity.this, ItemListActivity.class);
                    startActivity(intent);
                }
            });

//          Create the detail fragment and add it to the activity using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));


            ItemDetailFragment myfragment = new ItemDetailFragment();
            myfragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, myfragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
