package com.messwave.messwave;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private static TableLayout table;
    private static ImageView vk_icon;
    private static ImageView gmail_icon;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        view.setTag("ListFragment");
        table = (TableLayout) view.findViewById(R.id.mess_table);
        //vk_icon.setImageResource(R.mipmap.ic_vk);
        //gmail_icon.setImageResource(R.mipmap.ic_gmail);
        return view;
    }

   public void add_row(String icon, String mess) {
        Log.d("KATRIN", "IN ADD");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        ImageView img = (ImageView) tr.findViewById(R.id.image);

        TextView txt = (TextView) tr.findViewById(R.id.little_mes);
        txt.setText(mess);
        table.addView(tr);
       Log.d("KATRIN", "add");
    }
}
