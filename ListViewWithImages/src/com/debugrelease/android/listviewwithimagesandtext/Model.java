package com.debugrelease.android.listviewwithimagesandtext;

import java.util.ArrayList;

public class Model {

    public static ArrayList<Item> Items;

    public static void LoadModel() {

        Items = new ArrayList<Item>();
        Items.add(new Item(1, R.drawable.ic_action_alarm_2, "Alarm"));
        Items.add(new Item(2, R.drawable.ic_action_calculator, "Calculator"));
        Items.add(new Item(3, R.drawable.ic_action_google_play, "Play"));
        Items.add(new Item(4, R.drawable.ic_action_line_chart, "Line Chart"));
        Items.add(new Item(5, R.drawable.ic_action_location_2, "Location"));
        Items.add(new Item(6, R.drawable.ic_action_news, "News"));
        Items.add(new Item(7, R.drawable.ic_action_stamp, "Stamp"));

    }

    public static Item GetbyId(int id){

        for(Item item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }

}
