package com.sdzshn3.android.demoapp.Database.FavoriteItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FavoriteItemDao {

    @Insert
    void insert(FavoriteItem favoriteItem);

    @Update
    void update(FavoriteItem favoriteItem);

    @Delete
    void delete(FavoriteItem favoriteItem);

    @Query("DELETE FROM favorite_item_table")
    void deleteAllFavoriteItems();

    @Query("SELECT * FROM favorite_item_table")
    LiveData<List<FavoriteItem>> getAllFavoriteItems();
}
