package com.messwave.messwave;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.VKSdk;


public class SettingsFragment extends Fragment {


    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        view.findViewById(R.id.vk_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KATRIN", "Checking if logged");
                //VKSdk.initialize(getActivity());
                if (!VKSdk.isLoggedIn()) {
                    Log.d("KATRING", "NOT LOGGED");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    Log.d("KATRIN", "Start activity");
                    startActivity(intent);
                }
            }
        });
        return view;
    }

}
