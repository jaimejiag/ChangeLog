package com.example.jaimejimenez.versionslog.data.repositories;

import com.example.jaimejimenez.versionslog.data.db.dao.ChangelogChangesDAO;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.data.network.api.ChangelogClient;
import com.example.jaimejimenez.versionslog.view.versionchanges.interactor.ListChangesInteractor;

import java.util.List;

/**
 * Created by jaimejimenez on 19/03/18.
 */

public class ChangelogChangesRepository {
    private static ChangelogChangesRepository mInstance;
    private ChangelogChangesDAO mDao;


    private ChangelogChangesRepository() {
        mDao = new ChangelogChangesDAO();
    }


    public static ChangelogChangesRepository getInstance() {
        if (mInstance == null)
            mInstance = new ChangelogChangesRepository();

        return mInstance;
    }


    public void initializeChangesTable() {
        mDao.deleteAllData();
        ChangelogClient.getInstance().getChangesList();
    }


    public void updateChangesTable(List<ChangelogCambio> body) {
        List<ChangelogCambio> changesList = body;

        for (int i = 0; i < changesList.size(); i++)
            mDao.insert(changesList.get(i));

        ListChangesInteractor.databaseUpdated(false, true);
    }


    public List<ChangelogCambio> getAllVersionChanges(ChangelogVersion version) {
        return mDao.getAllChanges(version);
    }
}
