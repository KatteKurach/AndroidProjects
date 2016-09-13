package com.messwave.messwave;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MessageFragment extends Fragment {

    private TextView view_text;
    private EditText get_text;
    private ImageView sent;

    public void setView_text(String s){
        view_text.setText(s);
    }

    public void setGet_text(){
        get_text.setText("");
    }

    public String getGet_text(){
        return String.valueOf(get_text.getText());
    }

    public MessageFragment() {
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        view_text = (TextView) view.findViewById(R.id.text_mess);
        get_text = (EditText) view.findViewById(R.id.get_text);
        sent = (ImageView) view.findViewById(R.id.sent);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setView_text(getGet_text());
                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.like_btn);
                view.startAnimation(anim);
                Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                setGet_text();
            }
        });
        return view;
    }
}
