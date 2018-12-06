package com.sdzshn3.android.demoapp.Database.FavoriteItem;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_item_table")
public class FavoriteItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int item_id;
    private String date_added;

    public FavoriteItem(int item_id, String date_added) {
        this.item_id = item_id;
        this.date_added = date_added;
    }

    public int getId() {
        return id;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setId(int id) {
        this.id = id;
    }
}
