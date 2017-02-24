package com.example.android.Spanish.data;

import android.provider.BaseColumns;

/**
 * Created by zhang4all on 2/2/2017.
 */

public final class WordContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private WordContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class WordEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "myWords";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ENGLISH_WORD ="english";

        /**
         * Breed of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SPANISH_WORD = "spanish";


    }


}
