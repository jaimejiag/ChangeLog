package com.example.jaimejimenez.versionslog.view.versionchanges.presenters;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.view.versionchanges.contracts.ListChangesContract;
import com.example.jaimejimenez.versionslog.view.versionchanges.interactor.ListChangesInteractor;

import java.util.List;

/**
 * Created by jaimejimenez on 20/03/18.
 */

public class ListChangesPresenter implements ListChangesContract.Presenter, ListChangesContract.Interactor.ListChangesListener {
    private ListChangesContract.View mView;
    private ListChangesContract.Interactor mInteractor;


    public ListChangesPresenter(ListChangesContract.View mView) {
        this.mView = mView;
        mInteractor = new ListChangesInteractor(this);
    }


    @Override
    public void requestToUpdateDatabase() {
        mInteractor.updateDatabase();
    }


    @Override
    public void requestToLoadLastVersion() {
        mInteractor.loadLastVersion();
    }


    @Override
    public void requestToLoadVersionChanges(ChangelogVersion version) {
        mInteractor.loadVersionChanges(version);
    }


    @Override
    public void onUpdateDatabaseSuccess(boolean versionsUpdates, boolean changesUpdated) {
        mView.databaseIsUpdated(versionsUpdates, changesUpdated);
    }


    @Override
    public void onLoadVersionSuccess(ChangelogVersion version) {
        mView.showLastVersion(version);
    }


    @Override
    public void onLoadChangesSuccess(List<ChangelogCambio> changesList) {
        mView.showVersionChanges(changesList);
    }
}
