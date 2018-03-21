package com.example.jaimejimenez.versionslog.view.versionchanges.interactor;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogChangesRepository;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogVersionRepository;
import com.example.jaimejimenez.versionslog.view.versionchanges.contracts.ListChangesContract;

/**
 * Created by jaimejimenez on 20/03/18.
 */

public class ListChangesInteractor implements ListChangesContract.Interactor {
    private static ListChangesListener mListener;


    public ListChangesInteractor(ListChangesListener mListener) {
        this.mListener = mListener;
    }


    @Override
    public void updateDatabase() {
        ChangelogVersionRepository.getInstance().initializeVersionsTable();
        ChangelogChangesRepository.getInstance().initializeChangesTable();
    }


    @Override
    public void loadLastVersion() {
        mListener.onLoadVersionSuccess(ChangelogVersionRepository.getInstance().getLastVersion());
    }


    @Override
    public void loadVersionChanges(ChangelogVersion version) {
        mListener.onLoadChangesSuccess(ChangelogChangesRepository.getInstance().getAllVersionChanges(version));
    }


    public static void databaseUpdated(boolean versionsUpdated, boolean changesUpdated) {
        mListener.onUpdateDatabaseSuccess(versionsUpdated, changesUpdated);
    }
}
