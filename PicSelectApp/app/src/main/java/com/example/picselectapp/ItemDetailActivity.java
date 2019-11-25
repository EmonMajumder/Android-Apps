package com.example.picselectapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/** An activity representing a single Item detail screen.*/
public class ItemDetailActivity extends AppCompatActivity {

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state saved from previous configurations of this activity

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);

            //Create the image fragment and add it to the activity using a fragment transaction.
            Bundle iarguments = new Bundle();
            iarguments.putString(ImageFragment.ARG_ITEM_ID, getIntent().getStringExtra(ImageFragment.ARG_ITEM_ID));
            ImageFragment imageFragment = new ImageFragment();
            imageFragment.setArguments(iarguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .add(R.id.item_detail_container, imageFragment)
                    .commit();

            //Add the amination to make the glag jump
            ImageView imageView = findViewById(R.id.flag);
            animation = AnimationUtils.loadAnimation(this,R.anim.wave);
            imageView.startAnimation(animation);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this activity, the Up button is shown.
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
