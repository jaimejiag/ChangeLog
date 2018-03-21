package com.example.jaimejimenez.versionslog.data.db.models;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.jaimejimenez.versionslog.ChangelogApplication;
import com.example.jaimejimenez.versionslog.data.db.ChangelogContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jaimejimenez on 15/03/18.
 */

public class ChangelogOpenHelper extends SQLiteOpenHelper {
    private static ChangelogOpenHelper mInstance;
    private SQLiteDatabase mDatabase;
    private AtomicInteger mOpenCounter;


    private ChangelogOpenHelper() {
        super(ChangelogApplication.getContext(), ChangelogContract.DATABASE_NAME, null,
                ChangelogContract.DATABASE_VERSION);

        mOpenCounter = new AtomicInteger();
    }


    public static ChangelogOpenHelper getInstance() {
        if (mInstance == null)
            mInstance = new ChangelogOpenHelper();

        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.execSQL(ChangelogContract.ChangelogVersionEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ChangelogContract.ChangelogChangeEntry.SQL_CREATE_ENTRIES);

            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("ChangelogOpenHelper", e.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.execSQL(ChangelogContract.ChangelogChangeEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ChangelogContract.ChangelogVersionEntry.SQL_DELETE_ENTRIES);
            onCreate(sqLiteDatabase);

            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("ChangelogHelper", e.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys = 1");
        }
    }


    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1)
            mDatabase = getWritableDatabase();

        return mDatabase;
    }


    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0)
            mDatabase.close();
    }
}
