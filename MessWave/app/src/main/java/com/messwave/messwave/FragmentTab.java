package com.messwave.messwave;

/**
 * Created by ekaterinakurach on 9/7/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class FragmentTab extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragmentList = new ArrayList<>();


        public FragmentTab(FragmentManager fm) {
            super(fm);
        }

        @Override public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment){
            mFragmentList.add(fragment);
        }

        @Override public CharSequence getPageTitle(int position) {
            // return mFragmentListTitles.get(position);
            return null;
        }
}

