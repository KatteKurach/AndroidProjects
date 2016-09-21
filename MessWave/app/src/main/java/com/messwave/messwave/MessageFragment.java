package com.messwave.messwave;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.HashMap;
import java.util.Map;


public class MessageFragment extends Fragment {

    private TextView view_text;
    private EditText get_text;
    private ImageView sent;

    private int current_ID;

    public void setCurrent_ID(int id){
        current_ID = id;
    }

    public void setView_text(String s){
        view_text.setText(s);
    }

    public void setGet_text(){
        get_text.setText("");
    }

    public String getGet_text(){
        return String.valueOf(get_text.getText());
    }

    public int getCurrent_ID() { return current_ID; }

    public MessageFragment() {
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    public void sent_text(String mess) {

        Map<String, Object> m = new HashMap<> ();
        m.put("user_id", current_ID);
        Log.d("KATRIN", String.valueOf(current_ID));
        m.put("message", mess);
        VKParameters vkParameters = new VKParameters(m);
        VKRequest vkRequest = new VKRequest("messages.send", vkParameters);
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
               // Log.d("KATRIN", response.toString());
                return null;
            }
        });
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

                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.like_btn);
                view.startAnimation(anim);
                Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                sent_text(getGet_text());
                setGet_text();
            }
        });
        return view;
    }


}
