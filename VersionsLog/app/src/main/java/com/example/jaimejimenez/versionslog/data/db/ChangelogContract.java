package com.example.jaimejimenez.versionslog.data.db;

import android.provider.BaseColumns;

/**
 * Created by jaimejimenez on 15/03/18.
 */

public class ChangelogContract {
    public static final String DATABASE_NAME = "changelog.db";
    public static final int DATABASE_VERSION = 8;


    public static class ChangelogVersionEntry implements BaseColumns {
        public static final String TABLE_NAME = "changelogVersion";
        public static final String COLUMN_VERSION = "version";
        public static final String COLUMN_ID_APPLICATION = "idApplication";

        public static final String[] ALL_COLUMNS = new String[] {
                _ID, COLUMN_VERSION, COLUMN_ID_APPLICATION
        };

        public static final String ORDER_BY_ID_DESC = _ID + " DESC";

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY, " +
                "%s TEXT NOT NULL, " +
                "%s INT NOT NULL)",
                TABLE_NAME,
                _ID,
                COLUMN_VERSION,
                COLUMN_ID_APPLICATION);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_DELETE_ALL_DATA = String.format("DELETE FROM %s", TABLE_NAME);
    }


    public static class ChangelogChangeEntry implements BaseColumns {
        public static final String TABLE_NAME = "changelogChange";
        public static final String COLUMN_ID_VERSION = "idVersion";
        public static final String COLUMN_CHANGE = "change";
        public static final String COLUMN_ID_APPLICATION = "idApplication";

        /*public static final String REFERENCES_VERSION_ID =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE CASCADE",
                        ChangelogVersionEntry.TABLE_NAME, ChangelogVersionEntry.COLUMN_ID_VERSION);
                        */

        public static final String[] ALL_COLUMNS = new String[] {
                _ID, COLUMN_ID_VERSION, COLUMN_CHANGE, COLUMN_ID_APPLICATION
        };

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s INT NOT NULL, " +
                "%s TEXT NOT NULL, " +
                "%s INT NOT NULL)",
                TABLE_NAME,
                _ID,
                COLUMN_ID_VERSION,
                COLUMN_CHANGE,
                COLUMN_ID_APPLICATION);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_DELETE_ALL_DATA = String.format("DELETE FROM %s", TABLE_NAME);
    }
}
