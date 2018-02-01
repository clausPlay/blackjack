package com.example.mariu.blackjack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marius on 1/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_table";
    //col 1 - index 0
    public static final String COL_1 = "ID";
    //col 2 - index 1
    public static final String COL_2 = "NAME";
    //col 3 - index 2
    public static final String COL_3 = "SURNAME";
    //col 4 - index 3
    public static final String COL_4 = "PASSWORD";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, PASSWORD TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData( String name, String surname, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result==-1){return false;}else{return true;}
    }
    public Cursor getAllData(){
        SQLiteDatabase getDb = this.getWritableDatabase();
        Cursor res = getDb.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
