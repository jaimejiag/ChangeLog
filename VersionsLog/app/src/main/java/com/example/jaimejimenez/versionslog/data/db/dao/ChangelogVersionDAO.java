package com.example.jaimejimenez.versionslog.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jaimejimenez.versionslog.data.db.ChangelogContract;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogOpenHelper;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaimejimenez on 19/03/18.
 */

public class ChangelogVersionDAO {

    public List<ChangelogVersion> getAllVersions() {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        List<ChangelogVersion> versionsList = new ArrayList<>();
        ChangelogVersion version = null;

        Cursor cursor = database.query(ChangelogContract.ChangelogVersionEntry.TABLE_NAME,
                ChangelogContract.ChangelogVersionEntry.ALL_COLUMNS, null, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                version = new ChangelogVersion(
                        cursor.getInt(0), cursor.getString(1), cursor.getInt(2)
                );

                versionsList.add(version);
            } while (cursor.moveToNext());
        }

        ChangelogOpenHelper.getInstance().closeDatabase();
        return versionsList;
    }


    public ChangelogVersion getLastVersion() {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        ChangelogVersion version = null;

        Cursor cursor = database.query(ChangelogContract.ChangelogVersionEntry.TABLE_NAME,
                ChangelogContract.ChangelogVersionEntry.ALL_COLUMNS, null, null,
                null, null, ChangelogContract.ChangelogVersionEntry.ORDER_BY_ID_DESC, "1");

        if (cursor.moveToFirst()) {
            version = new ChangelogVersion(
                    cursor.getInt(0), cursor.getString(1), cursor.getInt(2)
            );
        }

        ChangelogOpenHelper.getInstance().closeDatabase();
        return version;
    }


    public long insert(ChangelogVersion version) {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        ContentValues values = getContentValues(version);

        long id = database.insert(ChangelogContract.ChangelogVersionEntry.TABLE_NAME, null, values);
        ChangelogOpenHelper.getInstance().closeDatabase();

        return id;
    }


    public void deleteAllData() {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        database.execSQL(ChangelogContract.ChangelogVersionEntry.SQL_DELETE_ALL_DATA);
        ChangelogOpenHelper.getInstance().closeDatabase();
    }


    private ContentValues getContentValues(ChangelogVersion version) {
        ContentValues values = new ContentValues();
        values.put(ChangelogContract.ChangelogVersionEntry._ID, version.getId());
        values.put(ChangelogContract.ChangelogVersionEntry.COLUMN_VERSION, version.getVersion());
        values.put(ChangelogContract.ChangelogVersionEntry.COLUMN_ID_APPLICATION, version.getIdAplicacion());

        return values;
    }
}
