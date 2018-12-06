package com.sdzshn3.android.demoapp.Database.Profile;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProfilesViewModel extends AndroidViewModel {
    private ProfilesRepository repository;
    private LiveData<List<Profile>> allProfiles;

    public ProfilesViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfilesRepository(application);
        allProfiles = repository.getAllProfiles();
    }

    public void insert(Profile profile) {
        repository.insert(profile);
    }

    public void update(Profile profile) {
        repository.update(profile);
    }

    public void delete(Profile profile) {
        repository.delete(profile);
    }

    public void deleteAllProfiles() {
        repository.deleteAllProfiles();
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }
}
