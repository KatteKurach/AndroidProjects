package com.messwave.messwave;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // VKSdk.initialize(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Toast t = Toast.makeText(this, String.valueOf(tabLayout.getTabCount()), Toast.LENGTH_LONG);
        //t.show();

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_build_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_assignment_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_local_post_office_black_24dp);

    }


    private void getLongPollServer() {
        try {
            Map<String, Object> vkparams = new HashMap<>();
            vkparams.put("count", 200);
            vkparams.put("out", 0);

            VKRequest obj = (VKRequest) VKApi.messages().get();
            obj.addExtraParameters(new VKParameters(vkparams));
            obj.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    super.onComplete(response);
                    JSONObject parser = null;
                    try {
                        parser = (response.json).getJSONObject("response");
                        JSONArray array = parser.getJSONArray("items");

                        for (int i = 0; i < array.length(); i++){
                           // Log.d("KATRIN", )

                            String chatname = array.getJSONObject(i).getString("title").toString();
                            String body = array.getJSONObject(i).getString("body").toString();
                            Log.d("KATRIN", chatname+": "+body);
                        }
                        //Log.d("KATRIN", array.getJSONObject(0).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Log.d("KATRIN", String.valueOf(response.json));
                }
            });

        } catch (Exception e) {
            Log.d("KATRIN", String.valueOf(e));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (VKSdk.isLoggedIn()) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    getLongPollServer();
                }
            });
            t.start();
        }
    }


    private void setupViewPager(ViewPager viewPager) {

        FragmentTab adapter = new FragmentTab(getSupportFragmentManager());
        adapter.addFragment(SettingsFragment.newInstance());
        adapter.addFragment(ListFragment.newInstance());
        adapter.addFragment(MessageFragment.newInstance());
        viewPager.setAdapter(adapter);
    }
}