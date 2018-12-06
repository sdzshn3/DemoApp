package com.sdzshn3.android.demoapp.Database.FavoriteItem;

import android.app.Application;
import android.os.AsyncTask;

import com.sdzshn3.android.demoapp.Database.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FavoriteItemRepository {
    private FavoriteItemDao favoriteItemDao;
    private LiveData<List<FavoriteItem>> allFavoriteItems;

    public FavoriteItemRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        favoriteItemDao = appDatabase.favoriteItemDao();
        allFavoriteItems = favoriteItemDao.getAllFavoriteItems();
    }

    public void insert(FavoriteItem favoriteItem) {
        new InsertFavoriteItemAsyncTask(favoriteItemDao).execute(favoriteItem);
    }

    public void update(FavoriteItem favoriteItem) {
        new UpdateFavoriteItemAsyncTask(favoriteItemDao).execute(favoriteItem);
    }

    public void delete(FavoriteItem favoriteItem) {
        new DeleteFavoriteItemAsyncTask(favoriteItemDao).execute(favoriteItem);
    }

    public void deleteAllFavoriteItems() {
        new DeleteAllFavoriteItemsAsyncTask(favoriteItemDao).execute();
    }

    public LiveData<List<FavoriteItem>> getAllFavoriteItems() {
        return allFavoriteItems;
    }

    private static class InsertFavoriteItemAsyncTask extends AsyncTask<FavoriteItem, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private InsertFavoriteItemAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }

        @Override
        protected Void doInBackground(FavoriteItem... favoriteItems) {
            favoriteItemDao.insert(favoriteItems[0]);
            return null;
        }
    }

    private static class UpdateFavoriteItemAsyncTask extends AsyncTask<FavoriteItem, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private UpdateFavoriteItemAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }

        @Override
        protected Void doInBackground(FavoriteItem... favoriteItems) {
            favoriteItemDao.update(favoriteItems[0]);
            return null;
        }
    }

    private static class DeleteFavoriteItemAsyncTask extends AsyncTask<FavoriteItem, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private DeleteFavoriteItemAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }

        @Override
        protected Void doInBackground(FavoriteItem... favoriteItems) {
            favoriteItemDao.delete(favoriteItems[0]);
            return null;
        }
    }

    private static class DeleteAllFavoriteItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private DeleteAllFavoriteItemsAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteItemDao.deleteAllFavoriteItems();
            return null;
        }
    }
}
