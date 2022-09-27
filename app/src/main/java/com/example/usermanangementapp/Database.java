package com.example.usermanangementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

//    table name
    public static final String DB_NAME = "USERDATA.DB";

    public static final String TABLE_NAME = "USERDETAILS";

    //    table columns
    public static final String _ID = "_id";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String NATIONALITY = "nationality";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

//    CREATE TABLE UserDetails
    private static final String CREATE_TABLE = "Create Table IF NOT EXISTS "+ TABLE_NAME +"("+ _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ FIRST_NAME +" TEXT NOT NULL, "+ LAST_NAME +" TEXT, "+ DOB +" TEXT, "+ GENDER +" TEXT, "+ NATIONALITY +" TEXT, "+ USERNAME +" TEXT," +
            PASSWORD +" TEXT)";
//    drop table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL(DROP_TABLE);
    }

    public Boolean insertuserdata(String Firstname, String Lastname, String DOB, String Gender,String Nationality,
                                  String Username, String Password )
    {
        SQLiteDatabase DB=this.getWritableDatabase(); //set the mode to receive values
        ContentValues contentValues=new ContentValues();
        contentValues.put("Firstname",Firstname);
        contentValues.put("Lastname",Lastname);
        contentValues.put("DOB",DOB);
        contentValues.put("Gender",Gender);
        contentValues.put("Nationality",Nationality);
        contentValues.put("Username",Username);
        contentValues.put("Password",Password);



        long result=DB.insert(TABLE_NAME,null,contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String Firstname, String Lastname, String DOB, String Gender,String Nationality,
                                  String Username, String Password){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Firstname",Firstname);
        contentValues.put("Lastname",Lastname);
        contentValues.put("DOB",DOB);
        contentValues.put("Gender",Gender);
        contentValues.put("Nationality",Nationality);
        contentValues.put("Username",Username);
        contentValues.put("Password",Password);

        Cursor cursor=DB.rawQuery("Select * from "+ TABLE_NAME +" where name=?", new String[]{Firstname});
        if(cursor.getCount()>0) {
            long result = DB.update(TABLE_NAME, contentValues, "name=?", new String[]{Firstname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }



    public Boolean deleteuserdata(String Firstname){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from "+ TABLE_NAME +" where name=?", new String[]{Firstname});
        if(cursor.getCount()>0) { //CHECK WHETHER THERE IS DATA IN THE TABLE
            long result = DB.delete(TABLE_NAME, "name=?", new String[]{Firstname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from "+TABLE_NAME, null);
        return cursor;


    }
}



