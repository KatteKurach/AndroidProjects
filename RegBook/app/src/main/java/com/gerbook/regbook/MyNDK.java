package com.gerbook.regbook;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ekaterinakurach on 9/26/16.
 */
public class MyNDK  {

    static {
        System.loadLibrary("MyLibrary");
    }

    public native String validate(String a);
}
