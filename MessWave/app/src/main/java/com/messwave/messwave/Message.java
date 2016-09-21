package com.messwave.messwave;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;

/**
 * Created by ekaterinakurach on 9/14/16.
 */
public class Message {

    private int sender_ID;
    private String recipient_ID;
    private String title;
    private String body;
    private String messanger;
    private boolean is_added;

    public Message(DBHelper dbHelper, int sender_ID, String type, String title, String body){
        this.sender_ID = sender_ID;
        this.title = title;
        this.body = body;
        this.messanger = type;
        this.is_added = false;
        SQLiteDatabase  database = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

    }

    public void setIs_added(){
        is_added = true;
    }

    public int getSender_ID(){ return sender_ID;}

    public boolean getIs_added(){
        return is_added;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public String getMessanger(){
        return messanger;
    }

}
