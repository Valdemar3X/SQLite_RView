package com.example.sqlite_rview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.sqlite_rview.MyConstants.DbConstants;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_STRUCTURE_ENTRIES =
                "CREATE TABLE " + DbConstants.TABLE_NAME + " (" +
                        DbConstants._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DbConstants.COLUMN_NAME + " TEXT NOT NULL, " +
                        DbConstants.COLUMN_AMOUNT + " INTEGER NOT NULL, "
                        + DbConstants.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                        +");";

        sqLiteDatabase.execSQL(SQL_CREATE_STRUCTURE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
