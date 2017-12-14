package com.dannextech.apps.eventmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by amoh on 12/8/2017.
 */

public class EventDbQueries {
    private SQLiteDatabase db;
    Context context;
    private EventDbHelper dbHelper;


    public EventDbQueries(Context context) {
        this.context = context;
        dbHelper =new EventDbHelper(context);
        dbHelper.getInstance(context);
    }

    public void saveEvent(String title, String description, String venue, String date, String time, String remind){
        db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(EventDbContract.EventDbDetails.COL_TITLE,title);
        val.put(EventDbContract.EventDbDetails.COL_DESCRIPTION,description);
        val.put(EventDbContract.EventDbDetails.COL_VENUE,venue);
        val.put(EventDbContract.EventDbDetails.COL_DATE,date);
        val.put(EventDbContract.EventDbDetails.COL_TIME,time);
        val.put(EventDbContract.EventDbDetails.COL_REMIND,remind);

        db.insert(EventDbContract.EventDbDetails.TABLE_NAME,null,val);
        db.close();

        Toast.makeText(context,"Event Saved Successfully",Toast.LENGTH_SHORT).show();
    }
    public void saveTask(String title, String description, String date, String time, String remind){
        db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(EventDbContract.TaskDbDetails.COL_TITLE,title);
        val.put(EventDbContract.TaskDbDetails.COL_DESCRIPTION,description);
        val.put(EventDbContract.TaskDbDetails.COL_DATE,date);
        val.put(EventDbContract.TaskDbDetails.COL_TIME,time);
        val.put(EventDbContract.TaskDbDetails.COL_REMIND,remind);

        db.insert(EventDbContract.TaskDbDetails.TABLE_NAME,null,val);

        db.close();
        Toast.makeText(context,"Task Saved Successfully",Toast.LENGTH_SHORT).show();
    }

    public EventTaskDetails[] retrieveTaskDetails() throws SQLException{
        db = dbHelper.getReadableDatabase();
        String mColumns[] = {EventDbContract.TaskDbDetails.COL_TITLE,EventDbContract.TaskDbDetails.COL_DESCRIPTION, EventDbContract.TaskDbDetails.COL_DATE, EventDbContract.TaskDbDetails.COL_TIME,EventDbContract.TaskDbDetails.COL_REMIND};
        String sortOrder = EventDbContract.TaskDbDetails.COL_DATE+" DESC";


        Cursor cursor = db.query(EventDbContract.TaskDbDetails.TABLE_NAME,mColumns,null,null,null,null,sortOrder);

        int numRows = (int) DatabaseUtils.queryNumEntries(db, EventDbContract.TaskDbDetails.TABLE_NAME);
        EventTaskDetails[] events = new EventTaskDetails[numRows];

        int position = 0;
        while (cursor.moveToNext()){
            events[position] = new EventTaskDetails();
            events[position].setTitle(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.TaskDbDetails.COL_TITLE)));
            events[position].setDescription(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.TaskDbDetails.COL_DESCRIPTION)));
            events[position].setDate(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.TaskDbDetails.COL_DATE)));
            events[position].setTime(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.TaskDbDetails.COL_TIME)));
            events[position].setRemindMe(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.TaskDbDetails.COL_REMIND)));



            position++;
        }
        cursor.close();
        db.close();
        return events;
    }

    public EventTaskDetails[] retrieveEventDetails() throws SQLException{
        db = dbHelper.getReadableDatabase();
        String mColumns[] = {EventDbContract.EventDbDetails.COL_TITLE, EventDbContract.EventDbDetails.COL_DESCRIPTION, EventDbContract.EventDbDetails.COL_VENUE, EventDbContract.EventDbDetails.COL_DATE, EventDbContract.EventDbDetails.COL_TIME, EventDbContract.EventDbDetails.COL_REMIND};
        String sortOrder = EventDbContract.EventDbDetails.COL_DATE+" ASC";


        Cursor cursor = db.query(EventDbContract.EventDbDetails.TABLE_NAME,mColumns,null,null,null,null,sortOrder);

        int numRows = (int) DatabaseUtils.queryNumEntries(db, EventDbContract.EventDbDetails.TABLE_NAME);
        EventTaskDetails[] tasks = new EventTaskDetails[numRows];

        int position = 0;
        while (cursor.moveToNext()){
            tasks[position] = new EventTaskDetails();
            tasks[position].setTitle(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.EventDbDetails.COL_TITLE)));
            tasks[position].setDate(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.EventDbDetails.COL_DATE)));
            tasks[position].setTime(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.EventDbDetails.COL_TIME)));
            tasks[position].setDescription(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.EventDbDetails.COL_DESCRIPTION)));
            tasks[position].setRemindMe(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.EventDbDetails.COL_REMIND)));
            tasks[position].setVenue(cursor.getString(cursor.getColumnIndexOrThrow(EventDbContract.EventDbDetails.COL_VENUE)));
            position++;
        }
        cursor.close();
        db.close();
        return tasks;
    }
}
