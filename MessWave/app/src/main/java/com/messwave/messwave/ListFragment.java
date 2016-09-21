package com.messwave.messwave;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.vk.sdk.VKSdk;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private static TableLayout table;
    private ViewPager viewPager;
    private DBHelper dbHelper;
    private SQLiteDatabase database;


    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new DBHelper(getActivity());
        database = dbHelper.getWritableDatabase();
        viewPager = (ViewPager) container;
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        table = (TableLayout) view.findViewById(R.id.mess_table);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (VKSdk.isLoggedIn()) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    display_messages();
                }
            });
            t.start();
        }
    }

    public void display_messages(){
        Cursor cursor = database.query(dbHelper.TABLE_MESSAGES, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            int body_index = cursor.getColumnIndex(dbHelper.KEY_TEXT);
            int title_index = cursor.getColumnIndex(dbHelper.KEY_TITLE);
            int time_index = cursor.getColumnIndex(dbHelper.KEY_TIME);
            int sender_index = cursor.getColumnIndex(dbHelper.KEY_SENDER);

            while (cursor.moveToNext()){
                add_row(cursor.getString(body_index), cursor.getInt(sender_index),
                         cursor.getString(title_index), cursor.getString(time_index));
            }
        }
        cursor.close();
    }

   public void add_row(final String body, final int sender_id, String title, final String time) {

       final LayoutInflater inflater = getActivity().getLayoutInflater();

       TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
       tr.setClickable(true);
       final int temp_id = sender_id;

       tr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
               vibe.vibrate(100);
               viewPager.setCurrentItem(2);
               MessageFragment fragment = (MessageFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:"
               +R.id.viewpager+":2");
               fragment.setView_text(body);
               fragment.setCurrent_ID(temp_id);
           }
       });

       tr.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
               vibe.vibrate(100);

               database.delete(dbHelper.TABLE_MESSAGES, dbHelper.KEY_TEXT + "=? and " + dbHelper.KEY_TIME + "=?",
                       new String[]{body, time});
               Toast.makeText(getActivity().getApplicationContext(), "Delete from database", Toast.LENGTH_SHORT).show();
               table.removeView(view);
               return true;
           }
       });

       TextView txt = (TextView) tr.findViewById(R.id.little_mes);
       txt.setText(title+":"+'\n'+body);
       table.addView(tr);
    }
}
