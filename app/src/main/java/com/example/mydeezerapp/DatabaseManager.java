package com.example.mydeezerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydb.sqlite";
    private static final int CURRENT_DB_VERSION = 1;
    public static DatabaseManager instance;

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, CURRENT_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table favorite"+"(id INTEGER, title TEXT, artist TEXT, album TEXT,"+"sampleUrl TEXT, link TEXT, coverUrl TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        switch (oldVersion){
            case 1:
                //Code sql 1->2
            case 2:
            //code sql 2->3
            case 3:
            //Code sql 3->4
            default:

        }
    }
}
