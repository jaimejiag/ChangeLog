package com.example.jaimejimenez.versionslog;

import android.app.Application;
import android.content.Context;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogOpenHelper;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogChangesRepository;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogVersionRepository;

/**
 * Created by jaimejimenez on 16/03/18.
 */

public class ChangelogApplication extends Application {
    private static ChangelogApplication mContext;
    private ChangelogOpenHelper mOpenHelper;


    public ChangelogApplication() {
        mContext = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mOpenHelper = ChangelogOpenHelper.getInstance();
        mOpenHelper.openDatabase();
    }


    public static Context getContext() {
        return mContext;
    }
}
