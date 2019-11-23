package com.example.picselectapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.picselectapp.dummy.DummyContent;

public class ImageFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    ImageView imageViewFlag;
    Integer[] imageId = {R.drawable.bangladesh, R.drawable.brazil, R.drawable.canada, R.drawable.china, R.drawable.france,
            R.drawable.germany,R.drawable.india, R.drawable.ireland, R.drawable.russia, R.drawable.usa};

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            //CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            ImageView appBar = activity.findViewById(R.id.flag);
            if (appBar != null) {
                //appBarLayout.setTitle(mItem.content);

                appBar.setImageResource(imageId[Integer.parseInt(mItem.id)-1]);
            }
        }
    }
}
