package com.example.jaimejimenez.versionslog.view.versionchanges.contracts;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;

import java.util.List;

/**
 * Created by jaimejimenez on 21/03/18.
 */

public interface SearchChangesContract {

    interface View {
        void populateSpinner(List<ChangelogVersion> versionsList);

        void showVersionChanges(List<ChangelogCambio> changesList);
    }


    interface Presenter {
        void requestToLoadAllVersions();

        void requestToLoadVersionChanges(ChangelogVersion version);
    }


    interface Interactor {
        void loadAllVersions();

        void loadVersionChanges(ChangelogVersion version);

        interface SearchChangesListener {
            void onLoadAllVersionsSuccess(List<ChangelogVersion> versionsList);
            void onLoadVersionChangesSuccess(List<ChangelogCambio> changesList);
        }
    }
}
