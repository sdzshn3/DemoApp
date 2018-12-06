package com.sdzshn3.android.demoapp.Database.Profile;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProfilesDao {

    @Insert
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Delete
    void delete(Profile profile);

    @Query("DELETE FROM profiles_table")
    void deleteAllProfiles();

    @Query("SELECT * FROM profiles_table")
    LiveData<List<Profile>> getAllProfiles();
}
