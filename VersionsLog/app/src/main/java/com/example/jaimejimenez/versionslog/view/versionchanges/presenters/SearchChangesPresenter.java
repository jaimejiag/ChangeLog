package com.example.jaimejimenez.versionslog.view.versionchanges.presenters;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.view.versionchanges.contracts.SearchChangesContract;
import com.example.jaimejimenez.versionslog.view.versionchanges.interactor.SearchChangesInteractor;

import java.util.List;

/**
 * Created by jaimejimenez on 21/03/18.
 */

public class SearchChangesPresenter implements SearchChangesContract.Presenter, SearchChangesContract.Interactor.SearchChangesListener {
    private SearchChangesContract.View mView;
    private SearchChangesContract.Interactor mInteractor;


    public SearchChangesPresenter(SearchChangesContract.View mView) {
        this.mView = mView;
        mInteractor = new SearchChangesInteractor(this);
    }


    @Override
    public void requestToLoadAllVersions() {
        mInteractor.loadAllVersions();
    }


    @Override
    public void requestToLoadVersionChanges(ChangelogVersion version) {
        mInteractor.loadVersionChanges(version);
    }


    @Override
    public void onLoadAllVersionsSuccess(List<ChangelogVersion> versionsList) {
        mView.populateSpinner(versionsList);
    }


    @Override
    public void onLoadVersionChangesSuccess(List<ChangelogCambio> changesList) {
        mView.showVersionChanges(changesList);
    }
}
