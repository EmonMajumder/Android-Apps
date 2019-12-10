package com.example.movietrailerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.movietrailerapp.moviecontent.MovieDbHelper;
import com.example.movietrailerapp.moviecontent.MovieItem;
import com.example.movietrailerapp.moviecontent.MyMovies;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    public MovieDbHelper moviedbhelper = new MovieDbHelper(this);
    public static List<MovieItem> allMoviesList = new ArrayList<MovieItem>();
    public static List<MovieItem> mValues;
    FloatingActionButton addmovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allMoviesList = moviedbhelper.getAllMovies();

        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        addmovie = findViewById(R.id.fab);
        addmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult( new Intent(ItemListActivity.this,AddMovieTrailer.class),1);
            }
        });
    }

        protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==1 && resultCode == RESULT_OK)
        {
            allMoviesList = moviedbhelper.getAllMovies();
            setContentView(R.layout.activity_item_list);
            View recyclerView = findViewById(R.id.item_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView);
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, MyMovies.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private ItemListActivity mParentActivity;
        //private List<MovieItem> mValues;
        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    MovieItem item = (MovieItem) view.getTag();
                    //mValues.remove(mValues.lastIndexOf(item));
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailActivity.ARG_ITEM_ID, String.valueOf(item.id));
                    context.startActivity(intent);
                }
        };


        SimpleItemRecyclerViewAdapter(ItemListActivity parent, List<MovieItem> items) {
            mValues = items;
            mParentActivity = parent;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(String.valueOf(mValues.get(position).id));
            holder.mContentView.setText(mValues.get(position).name);
            holder.mRatingView.setText(String.valueOf(mValues.get(position).rating));
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
            final TextView mRatingView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
                mRatingView = view.findViewById(R.id.mrating);
            }
        }
    }
}
