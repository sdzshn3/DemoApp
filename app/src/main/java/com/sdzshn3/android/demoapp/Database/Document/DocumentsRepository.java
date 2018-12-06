package com.sdzshn3.android.demoapp.Database.Document;

import android.app.Application;
import android.os.AsyncTask;

import com.sdzshn3.android.demoapp.Database.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Repository class for Documents. This is a mediator between ViewModel class and Data source
 */
public class DocumentsRepository {
    private DocumentsDao documentsDao;
    private LiveData<List<Document>> allDocuments;

    public DocumentsRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        documentsDao = appDatabase.documentsDao();
        allDocuments = documentsDao.getAllDocuments();
    }

    public void insert(Document document) {
        new InsertDocumentAsyncTask(documentsDao).execute(document);
    }

    public void update(Document document) {
        new UpdateDocumentAsyncTask(documentsDao).execute(document);
    }

    public void delete(Document document) {
        new DeleteDocumentAsyncTask(documentsDao).execute(document);
    }

    public void deleteAllDocuments() {
        new DeleteAllDocumentsAsyncTask(documentsDao).execute();
    }

    public LiveData<List<Document>> getAllDocuments() {
        return allDocuments;
    }

    private static class InsertDocumentAsyncTask extends AsyncTask<Document, Void, Void> {
        private DocumentsDao documentsDao;
        private InsertDocumentAsyncTask(DocumentsDao documentsDao) {
            this.documentsDao = documentsDao;
        }

        @Override
        protected Void doInBackground(Document... documents) {
            documentsDao.insert(documents[0]);
            return null;
        }
    }

    private static class UpdateDocumentAsyncTask extends AsyncTask<Document, Void, Void> {
        private DocumentsDao documentsDao;
        private UpdateDocumentAsyncTask(DocumentsDao documentsDao) {
            this.documentsDao = documentsDao;
        }

        @Override
        protected Void doInBackground(Document... documents) {
            documentsDao.update(documents[0]);
            return null;
        }
    }

    private static class DeleteDocumentAsyncTask extends AsyncTask<Document, Void, Void> {
        private DocumentsDao documentsDao;
        private DeleteDocumentAsyncTask(DocumentsDao documentsDao) {
            this.documentsDao = documentsDao;
        }

        @Override
        protected Void doInBackground(Document... documents) {
            documentsDao.delete(documents[0]);
            return null;
        }
    }

    private static class DeleteAllDocumentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private DocumentsDao documentsDao;
        private DeleteAllDocumentsAsyncTask(DocumentsDao documentsDao) {
            this.documentsDao = documentsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            documentsDao.deleteAllDocuments();
            return null;
        }
    }
}
