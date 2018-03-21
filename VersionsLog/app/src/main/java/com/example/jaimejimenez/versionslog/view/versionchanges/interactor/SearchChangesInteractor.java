package com.example.jaimejimenez.versionslog.view.versionchanges.interactor;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogChangesRepository;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogVersionRepository;
import com.example.jaimejimenez.versionslog.view.versionchanges.contracts.SearchChangesContract;

/**
 * Created by jaimejimenez on 21/03/18.
 */

public class SearchChangesInteractor implements SearchChangesContract.Interactor {
    private SearchChangesListener mListener;


    public SearchChangesInteractor(SearchChangesListener mListener) {
        this.mListener = mListener;
    }


    @Override
    public void loadAllVersions() {
        mListener.onLoadAllVersionsSuccess(ChangelogVersionRepository.getInstance().loadAllVersions());
    }


    @Override
    public void loadVersionChanges(ChangelogVersion version) {
        mListener.onLoadVersionChangesSuccess(ChangelogChangesRepository.getInstance().getAllVersionChanges(version));
    }
}
