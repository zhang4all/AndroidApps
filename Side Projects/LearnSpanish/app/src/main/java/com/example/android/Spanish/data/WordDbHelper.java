package com.example.android.Spanish.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.Spanish.data.WordContract.WordEntry;

/**
 * Created by zhang4all on 2/2/2017.
 */

public class WordDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = WordDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "words.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link WordDbHelper}.
     *
     * @param context of the app
     */
    public WordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_WORDS_TABLE =  "CREATE TABLE " + WordEntry.TABLE_NAME + " ("
                + WordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WordEntry.COLUMN_ENGLISH_WORD + " TEXT NOT NULL, "
                + WordEntry.COLUMN_SPANISH_WORD + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_WORDS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}