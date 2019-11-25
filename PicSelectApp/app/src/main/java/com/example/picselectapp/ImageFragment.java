package com.example.picselectapp;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.example.picselectapp.dummy.DummyContent;

public class ImageFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon screen orientation changes).
     */
    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment arguments.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();

            ImageView appBar = activity.findViewById(R.id.flag);
            if (appBar != null) {
                appBar.setImageResource(ItemListActivity.imageId.get(Integer.parseInt(mItem.id)-1));
            }
        }
    }
}
