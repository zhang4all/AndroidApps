package com.example.android.permission2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        sites = new ArrayList<>();
        sites.add("http://www.uic.edu");
        sites.add("http://www.google.com");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text, R.id.name, sites);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String site = sites.get(i);

                Intent intent = new Intent(ACTION_VIEW);
                intent.setData(Uri.parse(site));
                startActivity(intent);
            }
        });
    }





}
