package com.example.picselectapp;
import android.app.Activity;
import android.os.Bundle;
import com.example.picselectapp.dummy.DummyContent;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** A fragment representing a single Item detail screen.*/
public class ItemDetailFragment extends Fragment {

    /** The fragment argument representing the item ID that this fragment represents.*/
    public static final String ARG_ITEM_ID = "item_id";

    /** The dummy content this fragment is presenting.*/
    private DummyContent.DummyItem mItem;

    /**Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon screen orientation changes).*/
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {

            // Load the dummy content specified by the fragment arguments.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content detail as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }
        return rootView;
    }
}
