package com.example.jaimejimenez.versionslog.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jaimejimenez.versionslog.data.db.ChangelogContract;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogOpenHelper;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaimejimenez on 19/03/18.
 */

public class ChangelogChangesDAO {

    public List<ChangelogCambio> getAllChanges(ChangelogVersion version) {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        List<ChangelogCambio> changesList = new ArrayList<>();
        ChangelogCambio change = null;
        String selection = null;
        String[] selectionArgs = null;

        if (version != null) {
            selection = ChangelogContract.ChangelogChangeEntry.COLUMN_ID_VERSION + "=?";
            selectionArgs = new String[] { String.valueOf(version.getId()) };
        }

        Cursor cursor = database.query(ChangelogContract.ChangelogChangeEntry.TABLE_NAME,
                ChangelogContract.ChangelogChangeEntry.ALL_COLUMNS, selection, selectionArgs,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                change = new ChangelogCambio();
                change.setId(cursor.getInt(0));
                change.setIdVersion(cursor.getInt(1));
                change.setCambio(cursor.getString(2));
                change.setIdAplicacion(cursor.getInt(3));

                changesList.add(change);
            } while (cursor.moveToNext());
        }

        ChangelogOpenHelper.getInstance().closeDatabase();
        return changesList;
    }


    public long insert(ChangelogCambio change) {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        ContentValues values = getContentValues(change);

        long id = database.insert(ChangelogContract.ChangelogChangeEntry.TABLE_NAME, null, values);
        ChangelogOpenHelper.getInstance().closeDatabase();

        return id;
    }


    public void deleteAllData() {
        SQLiteDatabase database = ChangelogOpenHelper.getInstance().openDatabase();
        database.execSQL(ChangelogContract.ChangelogChangeEntry.SQL_DELETE_ALL_DATA);
        ChangelogOpenHelper.getInstance().closeDatabase();
    }


    private ContentValues getContentValues(ChangelogCambio change) {
        ContentValues values = new ContentValues();
        values.put(ChangelogContract.ChangelogChangeEntry.COLUMN_ID_VERSION, change.getIdVersion());
        values.put(ChangelogContract.ChangelogChangeEntry.COLUMN_CHANGE, change.getCambio());
        values.put(ChangelogContract.ChangelogChangeEntry.COLUMN_ID_APPLICATION, change.getIdAplicacion());

        return values;
    }
}
