package com.example.jaimejimenez.versionslog.view.versionchanges;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.jaimejimenez.versionslog.R;
import com.example.jaimejimenez.versionslog.adapters.ChangesAdapter;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.view.versionchanges.contracts.SearchChangesContract;
import com.example.jaimejimenez.versionslog.view.versionchanges.presenters.SearchChangesPresenter;

import java.util.List;


public class SearchView extends Fragment implements SearchChangesContract.View {
    public static final String TAG = "searchviewfragment";

    private Spinner spVersions;
    private TextView txvVersion;
    private RecyclerView rvList;

    private SearchChangesContract.Presenter mPresenter;
    private ChangesAdapter mAdapter;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;


    public static SearchView newInstance(Bundle bundle) {
        SearchView fragment = new SearchView();

        if (bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SearchChangesPresenter(this);
        mAdapter = new ChangesAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_view, container, false);

        spVersions = view.findViewById(R.id.sp_versions);
        txvVersion = view.findViewById(R.id.txv_version_changes);
        rvList = view.findViewById(R.id.rv_searchView);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mAdapter);

        mPresenter.requestToLoadAllVersions();

        initializeItemSelectedListener();
        spVersions.setOnItemSelectedListener(mItemSelectedListener);
    }


    @Override
    public void populateSpinner(List<ChangelogVersion> versionsList) {
        ArrayAdapter<ChangelogVersion> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, versionsList);

        spVersions.setAdapter(adapter);
    }


    @Override
    public void showVersionChanges(List<ChangelogCambio> changesList) {
        mAdapter.addAll(changesList);
    }


    private void initializeItemSelectedListener() {
        mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ChangelogVersion version = (ChangelogVersion) adapterView.getItemAtPosition(i);
                txvVersion.setText(getResources().getString(R.string.txv_version_changes) + " " + version.getVersion());
                mPresenter.requestToLoadVersionChanges(version);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }
}
