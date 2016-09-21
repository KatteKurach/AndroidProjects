package com.messwave.messwave;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ekaterinakurach on 9/19/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "messageDB";
    public static final String TABLE_MESSAGES = "messages";

    public static final String KEY_ID = "_id";
    public static final String KEY_SENDER = "sender";
    public static final String KEY_RECIPIENT = "recipient";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TEXT = "text";
    public static final String KEY_TIME = "time";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_MESSAGES + "(" +
                                KEY_ID + " integer primary key," +
                                KEY_SENDER + " text," + KEY_RECIPIENT + " text," +
                                KEY_TITLE + " text," + KEY_TEXT + " text," +
                                KEY_TIME + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_MESSAGES);
        onCreate(sqLiteDatabase);
    }
}
