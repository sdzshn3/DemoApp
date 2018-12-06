package com.sdzshn3.android.demoapp.Database.Document;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Model class for Documents
 */
@Entity(tableName = "document_table")
public class Document {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int user_id;
    private int profile_id;
    private int document;

    public Document(int user_id, int profile_id, int document) {
        this.user_id = user_id;
        this.profile_id = profile_id;
        this.document = document;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public int getDocument() {
        return document;
    }
}
