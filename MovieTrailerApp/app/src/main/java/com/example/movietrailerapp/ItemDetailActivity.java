package com.example.movietrailerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.movietrailerapp.moviecontent.MovieItem;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayerFragment;
import androidx.appcompat.widget.Toolbar;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;


import android.view.MenuItem;

import android.widget.Button;


/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {


    public static final int RECOVERY_DIALOG_REQUEST = 1;
    public static final String VIDEO_ID = "Your Video ID";
    public static final String ARG_ITEM_ID = "item_id";
    private MovieItem mItem;

    YouTubePlayerFragment youTubePlayerFragment;
    YouTubePlayerView youTubePlayerView;
    Button button;

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

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {

            //youTubePlayerView = findViewById(R.id.youtubeview);
            button = findViewById(R.id.btnplay);



//             Create the detail fragment and add it to the activity using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));


            ItemDetailFragment myfragment = new ItemDetailFragment();
            myfragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.youtubeframe, myfragment)
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
