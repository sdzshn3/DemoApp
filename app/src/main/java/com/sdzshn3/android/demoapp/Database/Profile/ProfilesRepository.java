package com.sdzshn3.android.demoapp.Database.Profile;

import android.app.Application;
import android.os.AsyncTask;

import com.sdzshn3.android.demoapp.Database.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProfilesRepository {
    private ProfilesDao profilesDao;
    private LiveData<List<Profile>> allProfiles;

    public ProfilesRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        profilesDao = appDatabase.profilesDao();
        allProfiles = profilesDao.getAllProfiles();
    }

    public void insert(Profile profile) {
        new InsertProfileAsyncTask(profilesDao).execute(profile);
    }

    public void update(Profile profile) {
        new UpdateProfileAsyncTask(profilesDao).execute(profile);
    }

    public void delete(Profile profile) {
        new DeleteProfileAsyncTask(profilesDao).execute(profile);
    }

    public void deleteAllProfiles() {
        new DeleteAllProfileAsyncTask(profilesDao).execute();
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }

    private static class InsertProfileAsyncTask extends AsyncTask<Profile, Void, Void> {
        private ProfilesDao profilesDao;
        private InsertProfileAsyncTask(ProfilesDao profilesDao) {
            this.profilesDao = profilesDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profilesDao.insert(profiles[0]);
            return null;
        }
    }

    private static class UpdateProfileAsyncTask extends AsyncTask<Profile, Void, Void> {
        private ProfilesDao profilesDao;
        private UpdateProfileAsyncTask(ProfilesDao profilesDao) {
            this.profilesDao = profilesDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profilesDao.update(profiles[0]);
            return null;
        }
    }

    private static class DeleteProfileAsyncTask extends AsyncTask<Profile, Void, Void> {
        private ProfilesDao profilesDao;
        private DeleteProfileAsyncTask(ProfilesDao profilesDao) {
            this.profilesDao = profilesDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profilesDao.delete(profiles[0]);
            return null;
        }
    }

    private static class DeleteAllProfileAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProfilesDao profilesDao;
        private DeleteAllProfileAsyncTask(ProfilesDao profilesDao) {
            this.profilesDao = profilesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            profilesDao.deleteAllProfiles();
            return null;
        }
    }

}
