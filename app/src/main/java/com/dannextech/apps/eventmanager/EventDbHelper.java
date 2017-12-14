package com.dannextech.apps.eventmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amoh on 12/7/2017.
 */

public class EventDbHelper extends SQLiteOpenHelper {
    private EventDbHelper mInstance = null;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "event_db";
    public static final String CREATE_TABLE_EVENTS = "CREATE TABLE " +
            EventDbContract.EventDbDetails.TABLE_NAME + "(" +
            EventDbContract.EventDbDetails._ID + " INTEGER PRIMARY KEY," +
            EventDbContract.EventDbDetails.COL_TITLE + " TEXT,"+
            EventDbContract.EventDbDetails.COL_DESCRIPTION + " TEXT," +
            EventDbContract.EventDbDetails.COL_VENUE + " TEXT," +
            EventDbContract.EventDbDetails.COL_DATE + " TEXT," +
            EventDbContract.EventDbDetails.COL_TIME + " TEXT," +
            EventDbContract.EventDbDetails.COL_REMIND + " TEXT)";
    public static final String CREATE_TABLE_TASKS = "CREATE TABLE " +
            EventDbContract.TaskDbDetails.TABLE_NAME + "(" +
            EventDbContract.TaskDbDetails._ID + " INTEGER PRIMARY KEY," +
            EventDbContract.TaskDbDetails.COL_TITLE + " TEXT,"+
            EventDbContract.TaskDbDetails.COL_DESCRIPTION + " TEXT," +
            EventDbContract.TaskDbDetails.COL_DATE + " TEXT," +
            EventDbContract.TaskDbDetails.COL_TIME + " TEXT," +
            EventDbContract.TaskDbDetails.COL_REMIND + " TEXT)";
    public static final String DELETE_TABLE_EVENTS = "DROP TABLE IF EXISTS "+ EventDbContract.EventDbDetails.TABLE_NAME;
    public static final String DELETE_TABLE_TASKS = "DROP TABLE IF EXISTS "+ EventDbContract.TaskDbDetails.TABLE_NAME;
    public EventDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_EVENTS);
        db.execSQL(DELETE_TABLE_TASKS);
        onCreate(db);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    public EventDbHelper getInstance(Context context){
        if (mInstance==null){
            mInstance = new EventDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }
}
