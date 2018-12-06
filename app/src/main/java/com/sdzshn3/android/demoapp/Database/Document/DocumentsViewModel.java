package com.sdzshn3.android.demoapp.Database.Document;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * This is a ViewModel class for Documents. All the document data is stored here to show in UI as a LiveData
 */
public class DocumentsViewModel extends AndroidViewModel {
    private DocumentsRepository repository;
    private LiveData<List<Document>> allDocuments;

    public DocumentsViewModel(@NonNull Application application) {
        super(application);
        repository = new DocumentsRepository(application);
        allDocuments = repository.getAllDocuments();
    }

    public void insert(Document document) {
        repository.insert(document);
    }

    public void update(Document document) {
        repository.update(document);
    }

    public void delete(Document document) {
        repository.delete(document);
    }

    public void deleteAllDocuments() {
        repository.deleteAllDocuments();
    }

    public LiveData<List<Document>> getAllDocuments() {
        return allDocuments;
    }
}
