package com.messwave.messwave;


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

    public ArrayList<Message> messages;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

    public void add_new_message(Message message) {
        if (messages == null) {
            messages = new ArrayList<Message>();
        }

        messages.add(message);
    }


    public void display_messages(){
        if (messages == null){
            return;
        }
        for (int i = 0; i < messages.size(); i++){
            if (!messages.get(i).getIs_added()){
                add_row(messages.get(i).getSender_ID(), messages.get(i).getTitle(),
                        messages.get(i).getBody());
                messages.get(i).setIs_added();
            }
        }
    }

   public void add_row(int id, String title, String mess) {
       if (getActivity() == null){
           Log.d("KATRIN", "null!!!!!!!");
       }
       final LayoutInflater inflater = getActivity().getLayoutInflater();

       TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
       tr.setClickable(true);
       final String temp_mess = mess;
       final int temp_id = id;
       tr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
               vibe.vibrate(100);
               viewPager.setCurrentItem(2);
               MessageFragment fragment = (MessageFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:"
               +R.id.viewpager+":2");
               fragment.setView_text(temp_mess);
               fragment.setCurrent_ID(temp_id);
           }
       });

       TextView txt = (TextView) tr.findViewById(R.id.little_mes);
       txt.setText(title+":"+'\n'+mess);
       table.addView(tr);
    }
}
