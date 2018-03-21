package com.example.jaimejimenez.versionslog.data.repositories;

import com.example.jaimejimenez.versionslog.data.db.dao.ChangelogVersionDAO;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.data.network.api.ChangelogClient;
import com.example.jaimejimenez.versionslog.view.versionchanges.interactor.ListChangesInteractor;

import java.util.List;

/**
 * Created by jaimejimenez on 19/03/18.
 */

public class ChangelogVersionRepository {
    private static ChangelogVersionRepository mInstance;
    private ChangelogVersionDAO mDao;


    private ChangelogVersionRepository() {
        mDao = new ChangelogVersionDAO();
    }


    public static ChangelogVersionRepository getInstance() {
        if (mInstance == null)
            mInstance = new ChangelogVersionRepository();

        return mInstance;
    }


    public void initializeVersionsTable() {
        mDao.deleteAllData();
        ChangelogClient.getInstance().getVersionsList();
    }


    public void updateVersionsTable(List<ChangelogVersion> body) {
        List<ChangelogVersion> versionsList = body;

        for (int i = 0; i < versionsList.size(); i++)
            mDao.insert(versionsList.get(i));

        ListChangesInteractor.databaseUpdated(true, false);
    }


    public ChangelogVersion getLastVersion() {
        return mDao.getLastVersion();
    }


    public List<ChangelogVersion> loadAllVersions() {
        return mDao.getAllVersions();
    }
}
