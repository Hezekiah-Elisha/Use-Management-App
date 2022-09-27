package com.example.usermanangementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class details extends AppCompatActivity {
    DatabaseHelper db;
//    TextView firstName, lastName, dob, gender, nationality;
    ListView lv;
    DBManager dbManager;
    SimpleAdapter Adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dbManager = new DBManager(this);
        dbManager.open();

        lv = findViewById(R.id.list_details);

        SimpleCursorAdapter simpleCursorAdapter = dbManager.populateListViewFromDB();
        lv.setAdapter(simpleCursorAdapter);
    }
}