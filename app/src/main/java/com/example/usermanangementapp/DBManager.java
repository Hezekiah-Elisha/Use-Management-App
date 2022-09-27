package com.example.usermanangementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

import java.net.PasswordAuthentication;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insert(String firstname, String lastname, String dob, String gender, String nationality, String username, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.FIRST_NAME, firstname);
        contentValue.put(DatabaseHelper.LAST_NAME, lastname);
        contentValue.put(DatabaseHelper.DOB, dob);
        contentValue.put(DatabaseHelper.GENDER, gender);
        contentValue.put(DatabaseHelper.NATIONALITY, nationality);
        contentValue.put(DatabaseHelper.USERNAME, username);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        double info = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        if (info == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor fetch(){
        String[] columns = new String[] {
                DatabaseHelper._ID, DatabaseHelper.FIRST_NAME,
                DatabaseHelper.DOB, DatabaseHelper.GENDER,
                DatabaseHelper.NATIONALITY, DatabaseHelper.USERNAME,
                DatabaseHelper.PASSWORD
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int update(long _id, String firstname, String lastname, String dob, String gender, String nationality, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FIRST_NAME, firstname);
        contentValues.put(DatabaseHelper.LAST_NAME, lastname);
        contentValues.put(DatabaseHelper.DOB, dob);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.NATIONALITY, nationality);
        contentValues.put(DatabaseHelper.USERNAME, username);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + "=" + _id, null );
        return i;
    }

    public void delete(String firstname) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.FIRST_NAME + "=" + firstname, null);
    }

    public boolean login(String username, String password) {
        String[] columns = {
                DatabaseHelper._ID
        };

        String selection = DatabaseHelper.USERNAME + " = ?" + " AND " + DatabaseHelper.PASSWORD + " = ?";

        String[] selectionArgs = {
                username, password
        };

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();

        cursor.close();

        if (cursorCount > 0){
            return true;
        } else {
            return false;
        }
    }

    public SimpleCursorAdapter populateListViewFromDB() {
        String[] columns = new String[] {
                DatabaseHelper._ID, DatabaseHelper.FIRST_NAME,
                DatabaseHelper.DOB, DatabaseHelper.GENDER,
                DatabaseHelper.NATIONALITY, DatabaseHelper.USERNAME,
                DatabaseHelper.PASSWORD
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        String[] fromFieldNames = {
                DatabaseHelper._ID, DatabaseHelper.FIRST_NAME,
                DatabaseHelper.DOB, DatabaseHelper.GENDER,
                DatabaseHelper.NATIONALITY, DatabaseHelper.USERNAME,
                DatabaseHelper.PASSWORD
        };
        int[] toViewIDs = {
                R.id.txtfirstnameLv, R.id.txtlastnameLv, R.id.txtdobLv,
                R.id.txtgenderLv, R.id.txtnationalityLv
        };

        SimpleCursorAdapter userAdapter = new SimpleCursorAdapter(
                context, R.layout.list_row, cursor, fromFieldNames, toViewIDs
        );

        return userAdapter;
    }
}
