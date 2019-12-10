package com.example.movietrailerapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.example.movietrailerapp.moviecontent.MovieDbHelper;
import com.example.movietrailerapp.moviecontent.MovieItem;
import com.example.movietrailerapp.moviecontent.MyMovies;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/** An activity representing a list of Items.*/
public class ItemListActivity<onCreateView> extends AppCompatActivity {

    public MovieDbHelper moviedbhelper = new MovieDbHelper(this);
    public static List<MovieItem> allMoviesList = new ArrayList<MovieItem>();
    public static List<MovieItem> mValues;
    public static MovieItem item;
    public FloatingActionButton addmovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get all the movies from database.
        allMoviesList = moviedbhelper.getAllMovies();
        MyMovies.ITEMS = moviedbhelper.getAllMovies();
        setContentView(R.layout.activity_item_list);

        //Show toolbar on top
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up the recycle view.
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        //Add functionality to the add movie button.
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

        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, MyMovies.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private ItemListActivity mParentActivity;
        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    item = (MovieItem) view.getTag();
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
                mIdView = view.findViewById(R.id.id_text);
                mContentView = view.findViewById(R.id.content);
                mRatingView = view.findViewById(R.id.mrating);
            }
        }
    }
}
