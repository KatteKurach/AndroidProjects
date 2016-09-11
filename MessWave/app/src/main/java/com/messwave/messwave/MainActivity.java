package com.messwave.messwave;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void setupViewPager(ViewPager viewPager) {

        FragmentTab adapter = new FragmentTab(getSupportFragmentManager());
        adapter.addFragment(SettingsFragment.newInstance());
        adapter.addFragment(ListFragment.newInstance());
        adapter.addFragment(MessageFragment.newInstance());
        viewPager.setAdapter(adapter);
    }
}