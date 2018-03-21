package com.example.jaimejimenez.versionslog;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.jaimejimenez.versionslog.R;
import com.example.jaimejimenez.versionslog.view.versionchanges.ListView;
import com.example.jaimejimenez.versionslog.view.versionchanges.SearchView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnvMenu;

    private ListView mListView;
    private SearchView mSearchView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnItemSelected;


    @Override
    protected void onStart() {
        super.onStart();
        initializeNavigationItemListener();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeNavigationItemListener();
        bnvMenu = findViewById(R.id.bnv_menu);
        bnvMenu.setOnNavigationItemSelectedListener(mOnItemSelected);

        mListView = (ListView) getSupportFragmentManager().findFragmentByTag(ListView.TAG);

        if (mListView == null)
            mListView = ListView.newInstance(null);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, mListView, ListView.TAG).commit();
    }


    private void initializeNavigationItemListener() {
        mOnItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()) {
                    case R.id.action_list:
                        mListView = (ListView) getSupportFragmentManager().findFragmentByTag(ListView.TAG);

                        if (mListView == null)
                            mListView = ListView.newInstance(null);

                        transaction.replace(R.id.frameLayout, mListView, ListView.TAG).commit();
                        break;

                    case R.id.action_search:
                        mSearchView = (SearchView) getSupportFragmentManager().findFragmentByTag(SearchView.TAG);

                        if (mSearchView == null)
                            mSearchView = SearchView.newInstance(null);

                        transaction.replace(R.id.frameLayout, mSearchView, SearchView.TAG).commit();
                        break;
                }

                return true;
            }
        };
    }
}
