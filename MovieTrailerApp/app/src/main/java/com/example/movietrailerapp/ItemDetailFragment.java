package com.example.movietrailerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movietrailerapp.moviecontent.MovieItem;
import com.example.movietrailerapp.moviecontent.MyMovies;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment{

    private String currentVideoID = "mPg_2eKpkog";
    private YouTubePlayer activePlayer;

//    public static final int RECOVERY_DIALOG_REQUEST = 1;
//    public static final String VIDEO_ID = "Your Video ID";
//    YouTubePlayer.OnInitializedListener onInitializedListener;
//    YouTubePlayerView youTubePlayerView;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private MovieItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = MyMovies.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.videolayout, container, false);

//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        //transaction.replace(R.id.videofragment, youTubePlayerFragment).commit();
//
//        youTubePlayerFragment.initialize(PlayerConfig.getApiKey(), new OnInitializedListener() {
//
//            @Override
//            public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
//                if (!wasRestored) {
//                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                    player.loadVideo("mPg_2eKpkog");
//                    player.play();
//                }
//            }
//

//            @Override
//            public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {
//                // YouTube error
//                String errorMessage = error.toString();
//                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
//            }
//        });

        View rootView = inflater.inflate(R.layout.item_detail, container, false);
//
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.description);
        }
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.name);
        }

        return rootView;
    }
}
