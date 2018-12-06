package com.sdzshn3.android.demoapp.Database.Item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ItemsDao {

    @Insert
    void insert(Item item);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM items_table")
    void deleteAllItems();

    @Query("SELECT * FROM items_table")
    LiveData<List<Item>> getAllItems();
}
