package com.sdzshn3.android.demoapp.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.sdzshn3.android.demoapp.Database.Document.Document;
import com.sdzshn3.android.demoapp.Database.Document.DocumentsDao;
import com.sdzshn3.android.demoapp.Database.FavoriteItem.FavoriteItem;
import com.sdzshn3.android.demoapp.Database.FavoriteItem.FavoriteItemDao;
import com.sdzshn3.android.demoapp.Database.Item.Item;
import com.sdzshn3.android.demoapp.Database.Item.ItemsDao;
import com.sdzshn3.android.demoapp.Database.Profile.Profile;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesDao;
import com.sdzshn3.android.demoapp.Database.User.User;
import com.sdzshn3.android.demoapp.Database.User.UserDao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Profile.class, Item.class, FavoriteItem.class, Document.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract UserDao userDao();
    public abstract ProfilesDao profilesDao();
    public abstract DocumentsDao documentsDao();
    public abstract FavoriteItemDao favoriteItemDao();
    public abstract ItemsDao itemsDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private ProfilesDao profilesDao;
        private ItemsDao itemsDao;

        private PopulateDbAsyncTask(AppDatabase db){
            userDao = db.userDao();
            profilesDao = db.profilesDao();
            itemsDao = db.itemsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("Dummy", "User", "",
                    "0123456789", "email@email.com", "College, City",
                    "City name", "true", "admin", "admin"));
            profilesDao.insert(new Profile("Profile", "desc", "opt1",
                    0, 0));
            itemsDao.insert(new Item("Philips", "home", "theatre", "5.1", "This product is a 5.1 home theatre", true, 1));
            itemsDao.insert(new Item("HP", "22 inches", "MOnitor", "22es", "This is my monitor", true, 1));
            itemsDao.insert(new Item("Moto", "G5", "Plus", "XT1686", "This is my phone", true, 2));
            itemsDao.insert(new Item("HP", "DVD", "Writer", "HP DVD1265", "This is my dvd writer of Desktop", true, 2));
            itemsDao.insert(new Item("Nokia", "7", "Plus", "NA", "This is my brother's phone", true, 3));
            itemsDao.insert(new Item("Philips", "home", "theatre", "5.1", "This product is a 5.1 home theatre", true, 1));
            itemsDao.insert(new Item("HP", "22 inches", "MOnitor", "22es", "This is my monitor", true, 1));
            itemsDao.insert(new Item("Moto", "G5", "Plus", "XT1686", "This is my phone", true, 2));
            itemsDao.insert(new Item("HP", "DVD", "Writer", "HP DVD1265", "This is my dvd writer of Desktop", true, 2));
            itemsDao.insert(new Item("Nokia", "7", "Plus", "NA", "This is my brother's phone", true, 3));
            itemsDao.insert(new Item("Philips", "home", "theatre", "5.1", "This product is a 5.1 home theatre", true, 1));
            itemsDao.insert(new Item("HP", "22 inches", "MOnitor", "22es", "This is my monitor", true, 1));
            itemsDao.insert(new Item("Moto", "G5", "Plus", "XT1686", "This is my phone", true, 2));
            itemsDao.insert(new Item("HP", "DVD", "Writer", "HP DVD1265", "This is my dvd writer of Desktop", true, 2));
            itemsDao.insert(new Item("Nokia", "7", "Plus", "NA", "This is my brother's phone", true, 3));
            itemsDao.insert(new Item("Philips", "home", "theatre", "5.1", "This product is a 5.1 home theatre", true, 1));
            itemsDao.insert(new Item("HP", "22 inches", "MOnitor", "22es", "This is my monitor", true, 1));
            itemsDao.insert(new Item("Moto", "G5", "Plus", "XT1686", "This is my phone", true, 2));
            itemsDao.insert(new Item("HP", "DVD", "Writer", "HP DVD1265", "This is my dvd writer of Desktop", true, 2));
            itemsDao.insert(new Item("Nokia", "7", "Plus", "NA", "This is my brother's phone", true, 3));
            return null;
        }
    }
}
