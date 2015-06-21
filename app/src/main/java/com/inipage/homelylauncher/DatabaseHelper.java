package com.inipage.homelylauncher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "DatabaseHelper";

    //Database basics
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 2;

    //Base columns
    public static final String COLUMN_ID = "_id";

    //Rows table
    public static final String TABLE_ROWS = "rows_table";
    public static final String COLUMN_DATA = "data_string";
    public static final String COLUMN_GRAPHIC = "graphic_res";
    public static final String COLUMN_GRAPHIC_PACKAGE = "graphic_package";
    public static final String COLUMN_ORDER = "ordering";
    public static final String COLUMN_TITLE = "title";

    //Hidden apps table
    public static final String TABLE_HIDDEN_APPS = "hidden_apps_table";
    public static final String COLUMN_PACKAGE = "package_name";
    public static final String COLUMN_ACTIVITY_NAME = "activity_name";

    //Widget table
    public static final String TABLE_WIDGETS = "widgets_table";
    public static final String COLUMN_WIDGET_ID = "widget_id";
    public static final String COLUMN_POSITION = "widget_position";
    public static final String COLUMN_SIZE_DP = "widget_size_dp";

    //Functions for creating it
    private static final String ROWS_TABLE_CREATE = "create table "
            + TABLE_ROWS +
            "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATA + " text not null, "
            + COLUMN_GRAPHIC + " text not null, "
            + COLUMN_GRAPHIC_PACKAGE + " text not null, "
            + COLUMN_ORDER + " integer not null, "
            + COLUMN_TITLE + " text not null);";
    private static final String HIDDEN_APPS_TABLE_CREATE = "create table "
            + TABLE_HIDDEN_APPS +
            "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_PACKAGE + " text not null, "
            + COLUMN_ACTIVITY_NAME + " text not null);";

    private static final String WIDGET_TABLE_CREATE = "create table "
            + TABLE_WIDGETS +
            "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WIDGET_ID + " integer not null, "
            + COLUMN_POSITION + " integer not null, "
            + COLUMN_SIZE_DP + " integer not null" + ");";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ROWS_TABLE_CREATE);
            db.execSQL(WIDGET_TABLE_CREATE);
            db.execSQL(HIDDEN_APPS_TABLE_CREATE);
            Log.d(TAG, "Created!");
        } catch (Exception e) {
            Log.d(TAG, "Failed to create!");
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1) //v1 didn't have a widget table
            db.execSQL(WIDGET_TABLE_CREATE);
    }
}