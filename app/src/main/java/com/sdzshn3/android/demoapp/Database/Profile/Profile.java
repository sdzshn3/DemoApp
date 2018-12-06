package com.sdzshn3.android.demoapp.Database.Profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profiles_table")
public class Profile {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String option1;
    private int document_id;
    private int user_id;

    public Profile(String name, String description, String option1, int document_id, int user_id) {
        this.name = name;
        this.description = description;
        this.option1 = option1;
        this.document_id = document_id;
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOption1() {
        return option1;
    }

    public int getDocument_id() {
        return document_id;
    }

    public int getUser_id() {
        return user_id;
    }
}
