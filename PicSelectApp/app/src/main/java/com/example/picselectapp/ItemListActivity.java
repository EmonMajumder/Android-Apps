package com.example.picselectapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.picselectapp.dummy.DummyContent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.picselectapp.ItemDetailFragment.ARG_ITEM_ID;

/** An activity representing a list of Items.*/
public class ItemListActivity extends AppCompatActivity {

    public static List<String>Lines;
    public static SharedPreferences sp;

    //List of images
    public static List<Integer>imageId = Arrays.asList(R.drawable.bangladesh,
                                                        R.drawable.brazil,
                                                        R.drawable.canada,
                                                        R.drawable.china,
                                                        R.drawable.france,
                                                        R.drawable.germany,
                                                        R.drawable.india,
                                                        R.drawable.ireland,
                                                        R.drawable.russia,
                                                        R.drawable.usa);

    public static List<String>Values = new ArrayList<String>();

    //Shared preferences data
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "0000000000";
    public static String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Put the names of countries from the string xml to a list.
        Lines = Arrays.asList(getResources().getStringArray(R.array.country_array));

        //Load data from shared preferences.
        loadData();

        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS));
    }

    //Save data to shared prefernece.
    public void saveData(int id)
    {
        Values.set(id-1,"1");
        sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        String p="";
        for(String s : Values)
        {
            if(p.isEmpty())
            {
                p+=s;
            }
            else
            {
                p+=",";
                p+=s;
            }
        }
        ed.putString(TEXT,p);
        ed.apply();
    }

    //Get data from shared prefernece.
    public void loadData()
    {
        sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sp.getString(TEXT,"0,0,0,0,0,0,0,0,0,0");
        Values = new ArrayList<String>(Arrays.asList(text.split(",")));
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        public final List<DummyContent.DummyItem> mValues;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

                //Remove a item from list view once clicked.
                mValues.remove(mValues.lastIndexOf(item));

                //save data in shared preference about the item clicked.
                saveData(Integer.parseInt(item.id));

                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ARG_ITEM_ID, item.id);
                intent.putExtra(ImageFragment.ARG_ITEM_ID,item.id);
                context.startActivity(intent);
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent, List<DummyContent.DummyItem> items) {
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
                holder.mIdView.setText(mValues.get(position).id);
                holder.mContentView.setText(mValues.get(position).content);
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

            ViewHolder(View view) {
                super(view);
                mIdView = view.findViewById(R.id.id_text);
                mContentView = view.findViewById(R.id.content);
            }
        }
    }
}
