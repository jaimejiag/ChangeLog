package com.example.jaimejimenez.versionslog.view.versionchanges.contracts;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;

import java.util.List;

/**
 * Created by jaimejimenez on 20/03/18.
 */

public interface ListChangesContract {

    interface View {
        void databaseIsUpdated(boolean versionsUpdated, boolean changesUpdated);

        void showLastVersion(ChangelogVersion version);

        void showVersionChanges(List<ChangelogCambio> changesList);
    }


    interface Presenter {
        void requestToUpdateDatabase();

        void requestToLoadLastVersion();

        void requestToLoadVersionChanges(ChangelogVersion version);
    }


    interface Interactor {
        void updateDatabase();

        void loadLastVersion();

        void loadVersionChanges(ChangelogVersion version);

        interface ListChangesListener {
            void onUpdateDatabaseSuccess(boolean versionsUpdates, boolean changesUpdated);
            void onLoadVersionSuccess(ChangelogVersion version);
            void onLoadChangesSuccess(List<ChangelogCambio> changesList);
        }
    }
}
