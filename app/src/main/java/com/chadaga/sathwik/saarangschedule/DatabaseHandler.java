package com.chadaga.sathwik.saarangschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sathwik on 07-07-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    int count;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "eventsManager";

    // events table name
    private static final String TABLE_EVENTS = "events";

    // events Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SUB_TITLE = "subtitle";
    private static final String KEY_DAY = "day";
    private static final String KEY_TIME = "time";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_SUB_TITLE + " TEXT,"
                + KEY_DAY + " TEXT,"
                + KEY_TIME + " TEXT" + ")";

        db.execSQL(CREATE_EVENTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new event
    void addEvent(Event event) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, event.getTitle()); // Event Title
        values.put(KEY_SUB_TITLE, event.getSubtitle()); // Event Subtitle
        values.put(KEY_DAY, event.getDay()); // Event Day
        values.put(KEY_TIME, event.getTime()); // Event image

        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Events on a specific day
    public List<Event> getEvents(int day) {

        List<Event> eventList = new ArrayList<Event>();

        // Select required events from Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE " + KEY_DAY + " == " + String.valueOf(day);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setTitle(cursor.getString(1));
                event.setSubtitle(cursor.getString(2));
                event.setDay(cursor.getString(3));
                event.setTime(cursor.getString(4));

                // Adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        // return event list
        return eventList;
    }

}
