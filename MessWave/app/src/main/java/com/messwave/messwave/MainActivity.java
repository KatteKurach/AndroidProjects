package com.messwave.messwave;

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

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ListFragment fragment;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_build_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_assignment_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_local_post_office_black_24dp);
    }

    private void get_user_name(String id, final String chat_name, final String body)
    {
        VKRequest user = (VKRequest) VKApi.users().get();

        final int temp_id = Integer.parseInt(id);

        user.addExtraParameter("user_ids", id);
        user.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONArray parser;
                try {
                    parser = (response.json).getJSONArray("response");

                    String first_name = parser.getJSONObject(0).getString("first_name");
                    String last_name = parser.getJSONObject(0).getString("last_name");

                    Message message1 = new Message(temp_id, "vk", first_name+" "+last_name, body);
                    fragment.add_new_message(message1);
                    fragment.display_messages();
                } catch (Exception e) {
                    Log.d("KATRIN", "no no no no");
                }
            }
        });
    }

    private void get_data(){

        Map<String, Object> vkparams = new HashMap<>();
        vkparams.put("count", 200);
        vkparams.put("out", 0);

        VKRequest obj = (VKRequest) VKApi.messages().get();
        obj.addExtraParameters(new VKParameters(vkparams));
        obj.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONObject parser;
                try {
                    parser = (response.json).getJSONObject("response");
                    JSONArray array = parser.getJSONArray("items");

                    for (int i = 0; i < array.length(); i++){
                        String chat_name = array.getJSONObject(i).getString("title").toString();
                        String body = array.getJSONObject(i).getString("body").toString();
                        String user_id = array.getJSONObject(i).getString("user_id").toString();

                        if (chat_name == " ... "){
                            chat_name = "";
                        }
                        get_user_name(user_id, chat_name, body);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLongPollServer() {
        try {
            get_data();
        } catch (Exception e) {
            Log.d("KATRIN", e.toString());
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
        fragment = ListFragment.newInstance();
        adapter.addFragment(fragment);
        adapter.addFragment(MessageFragment.newInstance());

        viewPager.setAdapter(adapter);
    }
}