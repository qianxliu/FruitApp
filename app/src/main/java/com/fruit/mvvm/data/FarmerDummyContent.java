package com.fruit.mvvm.data;

import android.graphics.drawable.Drawable;

import com.fruit.mvvm.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class FarmerDummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        DummyItem dummyItem = new DummyItem("农户" + position + "号", "占有土地面积（亩）:" + 100, "榆林市·横山县", makeDetails());

        /*
        switch (position) {
            case 1:
                dummyItem.addDrawable(R.drawable.field1);
                break;
            case 2:
                dummyItem.addDrawable(R.drawable.field2);
                break;
            case 3:
                dummyItem.addDrawable(R.drawable.field3);
                break;
            case 4:
                dummyItem.addDrawable(R.drawable.field4);
                break;
            default:
                dummyItem.addDrawable(R.drawable.cutecat);
                break;
        }

         */
        return dummyItem;
    }

    private static String[] makeDetails() {
        //StringBuilder builder = new StringBuilder();
        //builder.append("可种植蔬果种类").append(position);

        String[] items = {
                "适宜种植蔬果种类",
                "苹果",
                "菠菜",
                "玉米"
        };

        /*
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        */
        return items;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String location;
        public final String area;
        public final String[] details;
        public Drawable drawable;

        public DummyItem(String id, String area, String location, String[] details) {
            this.id = id;

            this.location = location;
            this.area = area;
            this.details = details;
        }

        public void addDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}