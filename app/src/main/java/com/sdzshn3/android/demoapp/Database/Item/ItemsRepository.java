package com.sdzshn3.android.demoapp.Database.Item;

import android.app.Application;
import android.os.AsyncTask;

import com.sdzshn3.android.demoapp.Database.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ItemsRepository {
    private ItemsDao itemsDao;
    private LiveData<List<Item>> allItems;

    public ItemsRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        itemsDao = appDatabase.itemsDao();
        allItems = itemsDao.getAllItems();
    }



    public void insert(Item item) {
        new InsertItemAsyncTask(itemsDao).execute(item);
    }

    public void update(Item item) {
        new UpdateItemAsyncTask(itemsDao).execute(item);
    }

    public void delete(Item item) {
        new DeleteItemAsyncTask(itemsDao).execute(item);
    }

    public void deleteAllItems() {
        new DeleteAllItemsAsyncTask(itemsDao).execute();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemsDao itemsDao;
        private InsertItemAsyncTask(ItemsDao itemsDao) {
            this.itemsDao = itemsDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemsDao.insert(items[0]);
            return null;
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemsDao itemsDao;
        private UpdateItemAsyncTask(ItemsDao itemsDao) {
            this.itemsDao = itemsDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemsDao.update(items[0]);
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemsDao itemsDao;
        private DeleteItemAsyncTask(ItemsDao itemsDao) {
            this.itemsDao = itemsDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemsDao.delete(items[0]);
            return null;
        }
    }

    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemsDao itemsDao;
        private DeleteAllItemsAsyncTask(ItemsDao itemsDao) {
            this.itemsDao = itemsDao;
        }

        @Override
        protected Void doInBackground(Void... items) {
            itemsDao.deleteAllItems();
            return null;
        }
    }
}
