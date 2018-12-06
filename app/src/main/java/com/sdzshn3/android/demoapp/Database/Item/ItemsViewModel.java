package com.sdzshn3.android.demoapp.Database.Item;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ItemsViewModel extends AndroidViewModel {
    private ItemsRepository repository;
    private LiveData<List<Item>> allItems;

    public ItemsViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemsRepository(application);
        allItems = repository.getAllItems();
    }

    public void refreshData() {
        allItems = repository.getAllItems();
    }

    public void insert(Item item) {
        repository.insert(item);
    }

    public void update(Item item) {
        repository.update(item);
    }

    public void delete(Item item) {
        repository.delete(item);
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }
}
