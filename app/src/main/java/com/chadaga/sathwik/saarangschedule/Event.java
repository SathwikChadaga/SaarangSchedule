package com.chadaga.sathwik.saarangschedule;

/**
 * Created by Sathwik on 07-07-2016.
 */
public class Event {

    //private variables
    int _id;
    String _time;
    String _title;
    String _subtitle;
    String _day;

    // Empty constructor
    public Event() {

    }

    // constructor
    public Event(int id, String title, String subtitle, String day, String time) {
        this._id = id;
        this._title = title;
        this._subtitle = subtitle;
        this._day = day;
        this._time = time;
    }

    // constructor
    public Event(String title, String subtitle, String day, String time) {
        this._title = title;
        this._subtitle = subtitle;
        this._day = day;
        this._time = time;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting ID
    public void setID(int id) {
        this._id = id;
    }

    // getting title
    public String getTitle() {
        return this._title;
    }

    // setting title
    public void setTitle(String title) {
        this._title = title;
    }

    // getting subtitle
    public String getSubtitle() {
        return this._subtitle;
    }

    // setting subtitle
    public void setSubtitle(String subtitle) {
        this._subtitle = subtitle;
    }

    // getting day
    public String getDay() {
        return this._day;
    }

    // setting day
    public void setDay(String day) {
        this._day = day;
    }

    //settint image id
    public void setTime(String time){
        this._time = time;
    }

    //getting image id
    public String getTime(){
        return _time;
    }

}
