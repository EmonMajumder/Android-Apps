package com.example.movietrailerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends YouTubeBaseActivity
implements YouTubePlayer.OnInitializedListener{


    public static final int RECOVERY_DIALOG_REQUEST = 1;
    public static final String VIDEO_ID = "Your Video ID";

    YouTubePlayerFragment youTubePlayerFragment;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        //setSupportActionBar(toolbar);
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(PlayerConfig.getApiKey(),this);

        // Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//
//            //youTubePlayerView = findViewById(R.id.youtubeview);
//            button = findViewById(R.id.btnplay);
//            //onInitializedListener = new YouTubePlayer.OnInitializedListener(){
//                //@Override
//            };
//
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    youTubePlayerView.initialize(PlayerConfig.getApiKey(),onInitializedListener);
//                }
//            });

            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
//            ItemDetailFragment fragment = new ItemDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.item_detail_container, fragment)
//                    .commit();

    }

    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
    {
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();
        }
        else
        {
            String errormessage = String.format("There was and error (%1$s)",youTubeInitializationResult.toString());
            Toast.makeText(this, errormessage, Toast.LENGTH_LONG).show();
        }
    }

    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean a)
    {
        if(!a)
        {
            youTubePlayer.cueVideo("W4hTJybfU7s");
        }
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if(requestCode == RECOVERY_DIALOG_REQUEST)
        {
            getYouTubePlayerProvider().initialize(PlayerConfig.getApiKey(),this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView) findViewById(R.id.youtubeplayerfragment);
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