package com.sdzshn3.android.demoapp.Database.Item;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String subtitle1;
    private String subtitle2;
    private String note;
    private String description;
    private boolean status;
    private int profileId;

    public Item(String title, String subtitle1, String subtitle2, String note, String description, boolean status, int profileId) {
        this.title = title;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.note = note;
        this.description = description;
        this.status = status;
        this.profileId = profileId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle1() {
        return subtitle1;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public String getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public int getProfileId() {
        return profileId;
    }
}
