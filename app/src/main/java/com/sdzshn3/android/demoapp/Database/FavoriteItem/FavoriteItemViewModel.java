package com.sdzshn3.android.demoapp.Database.FavoriteItem;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavoriteItemViewModel extends AndroidViewModel {
    private FavoriteItemRepository repository;
    private LiveData<List<FavoriteItem>> allFavoriteItems;

    public FavoriteItemViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteItemRepository(application);
        allFavoriteItems = repository.getAllFavoriteItems();
    }

    public void insert(FavoriteItem favoriteItem) {
        repository.insert(favoriteItem);
    }

    public void update(FavoriteItem favoriteItem) {
        repository.update(favoriteItem);
    }

    public void delete(FavoriteItem favoriteItem) {
        repository.delete(favoriteItem);
    }

    public void deleteAllFavoriteItems() {
        repository.deleteAllFavoriteItems();
    }

    public LiveData<List<FavoriteItem>> getAllFavoriteItems() {
        return allFavoriteItems;
    }
}
