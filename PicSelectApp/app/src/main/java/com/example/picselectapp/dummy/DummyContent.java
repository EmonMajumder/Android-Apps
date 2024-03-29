package com.example.picselectapp.dummy;

import com.example.picselectapp.ItemListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by Android template wizards.
 */


public class DummyContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            if(ItemListActivity.Values.get(i).equals("0"))
            {
                addItem(createDummyItem(i));
            }
        }
    }

    //Add dummy Items to list.
    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    //Create dummy items
    private static DummyItem createDummyItem(int position) {

        return new DummyItem(String.valueOf(position+1), ItemListActivity.Lines.get(position), makeDetails(position));
    }

    //Create a description for the item.
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("This flag id: ").append(position+1);

        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
