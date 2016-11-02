package com.pcsalt.example.searchtext.utils;

import android.widget.TextView;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public final class Utils {
    private Utils() {
    }

    public static String getText(TextView textView) {
        if (textView == null)
            return "";
        return textView.getText().toString().trim();
    }
}
