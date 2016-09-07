package com.messwave.messwave;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {android.R.drawable.ic_menu_camera,android.R.drawable.ic_menu_agenda,android.R.drawable.ic_menu_add};

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {

        FragmentTab adapter = new FragmentTab(getSupportFragmentManager());
        adapter.addFragment(Tab.newInstance("1"));
        adapter.addFragment(Tab.newInstance("2"));
        adapter.addFragment(Tab.newInstance("3"));
        viewPager.setAdapter(adapter);
    }
}