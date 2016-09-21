package com.messwave.messwave;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ekaterinakurach on 9/14/16.
 */
public class Message {

    private int sender_ID;

    private String recipient_ID;
    private String title;
    private String body;
    private String time;

    private DBHelper dbHelper;

    private boolean is_added;

    public Message(DBHelper dbHelper, int sender_ID, String recipient_ID,
                   String title, String body){
        this.dbHelper = dbHelper;
        this.sender_ID = sender_ID;
        this.recipient_ID = recipient_ID;
        this.title = title;
        this.body = body;
        time = getCurrTime();
        this.is_added = false;

        add_database();
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

    public String getCurrTime() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("DD:MM:yy HH:mm:ss");
        return sdf1.format(new Date());
    }

    public void add_database(){
        SQLiteDatabase  database = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.KEY_SENDER, sender_ID);
        contentValues.put(dbHelper.KEY_RECIPIENT, recipient_ID);
        contentValues.put(dbHelper.KEY_TITLE, title);
        contentValues.put(dbHelper.KEY_TEXT, body);
        contentValues.put(dbHelper.KEY_TIME, time);

        database.insert(dbHelper.TABLE_MESSAGES, null, contentValues);
    }

}
