package com.gerbook.regbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ekaterinakurach on 9/26/16.
 */
public class NewNote extends Activity {

    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        dBhelper = new DBhelper(this);
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(((EditText) findViewById(R.id.name)).getText());
                String number = String.valueOf(((EditText) findViewById(R.id.number)).getText());
                add_database(name, number);
                MyNDK d = new MyNDK();
                Log.d("KATRIN", String.valueOf(d.getStat("+")));
                Log.d("KATRIN", "HEEAR");
                Intent intent = new Intent(NewNote.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add_database(String name, String number){
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dBhelper.KEY_NAME, name);
        contentValues.put(dBhelper.KEY_NUMBER, number);
        contentValues.put(dBhelper.KEY_COUNT, 0);

        database.insert(dBhelper.TABLE_BOOK, null, contentValues);
    }
}
