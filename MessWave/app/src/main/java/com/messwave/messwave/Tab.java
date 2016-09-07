package com.messwave.messwave;

/**
 * Created by ekaterinakurach on 9/7/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab extends Fragment {
        private String number;

        public void setNumber(String s){
            number = s;
        }

        public Tab() {

        }

        public static Tab newInstance(String arg) {
            Tab tabFragmentOne = new Tab();
            tabFragmentOne.setNumber(arg);
            return tabFragmentOne;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (number == "1") {
                return inflater.inflate(R.layout.settings, container, false);
            }
            if (number == "2"){
                return inflater.inflate(R.layout.list_messages, container, false);
            } else {
                return inflater.inflate(R.layout.message, container, false);
            }
        }
    }
