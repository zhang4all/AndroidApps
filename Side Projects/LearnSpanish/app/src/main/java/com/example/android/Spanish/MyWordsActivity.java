/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.Spanish;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.android.Spanish.data.WordContract.WordEntry;
import com.example.android.Spanish.data.WordDbHelper;

import java.util.ArrayList;

import static com.example.android.Spanish.R.layout.my_words;


public class MyWordsActivity extends AppCompatActivity  {

    private WordDbHelper mDbHelper;
    private SQLiteDatabase db;

    private String newWordString;
    private ArrayList<Word> words;
    private  WordAdapter adapter;
    private ListView listView;
    private EditText newWord;
    boolean isClicked = true;
    private PopupWindow deleteWindow;
    private LayoutInflater layoutInflater;
    private Word wordToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my_words);

        // Create a list of words
        words = new ArrayList<>();

        // add these two to the db
        mDbHelper = new WordDbHelper(this);
        db = mDbHelper.getReadableDatabase();

        String englishWord="";
        String spanishWord="";

        // populates the arraylist for the adapter every time
        Cursor allRows = db.rawQuery("SELECT * FROM " + WordEntry.TABLE_NAME, null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            do {
                int i=0;
                for (String name: columnNames) {
                    Log.i("name", allRows.getString(allRows.getColumnIndex(name)));
                    if (i==1) {
                        englishWord = allRows.getString(allRows.getColumnIndex(name));
                    }
                    if (i==2) {
                        spanishWord = allRows.getString(allRows.getColumnIndex(name));
                    }
                    i++;
                }
                words.add(new Word(englishWord, spanishWord));
            } while (allRows.moveToNext());
        }

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        adapter = new WordAdapter(this, words, R.color.category_myWords);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        listView = (ListView) findViewById(R.id.myWordsList);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file

                // Get the {@link Word} object at the given position the user clicked on
                wordToDelete = words.get(position);

                // set the popup window here
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup,null);
                deleteWindow = new PopupWindow(container, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                deleteWindow.showAtLocation(findViewById(R.id.linear), Gravity.CENTER, 0, 0);

                container.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // delete from database
                        //db.delete(WordEntry.TABLE_NAME, WordEntry.COLUMN_ENGLISH_WORD + "=" + wordToDelete.getDefaultTranslation(), null);

                        words.remove(wordToDelete);
                        Log.i("word to delete", wordToDelete.getDefaultTranslation());

                        adapter = new WordAdapter(MyWordsActivity.this, words, R.color.category_myWords);
                        listView = (ListView) findViewById(R.id.myWordsList);
                        listView.setAdapter(adapter);
                        db.delete(WordEntry.TABLE_NAME, WordEntry.COLUMN_ENGLISH_WORD + " = ?", new String[] { wordToDelete.getDefaultTranslation() });

                        deleteWindow.dismiss();
                    }
                });

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        deleteWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }


    private void updateUi(String translation) {
        words.add(new Word(newWordString, translation));
        adapter = new WordAdapter(this, words, R.color.category_myWords);
        listView = (ListView) findViewById(R.id.myWordsList);
        listView.setAdapter(adapter);

        ContentValues values = new ContentValues();

        values.put(WordEntry.COLUMN_ENGLISH_WORD, newWordString);
        values.put(WordEntry.COLUMN_SPANISH_WORD, translation);

        long newRowId = db.insert(WordEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving word", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Word saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    public void search(View view) {
        EditText newWord = (EditText) findViewById(R.id.newWord);
        newWordString = newWord.getText().toString();
        // if it contains spaces, parse a "%20" into it
        String newWordStringUpdated = newWordString.replace(" ", "%20");
        Log.i("search", newWordStringUpdated);
        String stringURL = "https://translation.googleapis.com/language/translate/v2?key=AIzaSyC5cX0CPbzACGyJsQRn0WmpedM5XwYt4VY&source=en&target=es&q=";
        stringURL = stringURL + newWordStringUpdated;

        Log.i("search", stringURL);

        TranslationAsyncTask task = new TranslationAsyncTask();
        task.execute(stringURL);
        newWord.setText("");
    }


    private class TranslationAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            if (strings[0]==null) {
                return null;
            }

            String translation = QueryUtils.fetchTranslation(strings[0]);

            return translation;
        }


        @Override
        protected void onPostExecute(String translation) {
            if (translation == null) {
                return;
            }

            updateUi(translation);
        }
    }
}
