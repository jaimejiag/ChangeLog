package com.example.jaimejimenez.versionslog.view.versionchanges;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jaimejimenez.versionslog.R;
import com.example.jaimejimenez.versionslog.adapters.ChangesAdapter;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.view.versionchanges.contracts.ListChangesContract;
import com.example.jaimejimenez.versionslog.view.versionchanges.presenters.ListChangesPresenter;

import java.util.List;


public class ListView extends Fragment implements ListChangesContract.View {
    public static final String TAG = "listviewfragment";

    private RecyclerView rvList;
    private TextView txvVersion;

    private ListChangesContract.Presenter mPresenter;
    private ChangesAdapter mAdapter;
    private boolean mVersionsFlag;
    private boolean mChangesFlag;
    private ChangelogVersion mVersion;


    public static ListView newInstance(Bundle bundle) {
        ListView fragment = new ListView();

        if (bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ListChangesPresenter(this);
        mAdapter = new ChangesAdapter();
        mVersionsFlag = false;
        mChangesFlag = false;
        mVersion = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        rvList = view.findViewById(R.id.rv_listView);
        txvVersion = view.findViewById(R.id.txv_list_version);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.requestToUpdateDatabase();
        rvList.setAdapter(mAdapter);
    }


    @Override
    public void databaseIsUpdated(boolean versionsUpdated, boolean changesUpdated) {
        if (versionsUpdated)
            mVersionsFlag = versionsUpdated;

        if (changesUpdated)
            mChangesFlag = changesUpdated;

        if (mVersionsFlag && mChangesFlag) {
            mPresenter.requestToLoadLastVersion();
            mPresenter.requestToLoadVersionChanges(mVersion);
        }
    }


    @Override
    public void showLastVersion(ChangelogVersion version) {
        if (version != null) {
            txvVersion.setText("Versión: " + version.getVersion());
            mVersion = version;
        }
    }


    @Override
    public void showVersionChanges(List<ChangelogCambio> changesList) {
        mAdapter.addAll(changesList);
    }
}
