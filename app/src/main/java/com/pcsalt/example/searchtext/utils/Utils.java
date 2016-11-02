package com.pcsalt.example.searchtext.utils;

import android.content.Context;
import android.widget.TextView;

import com.pcsalt.example.searchtext.BuildConfig;
import com.pcsalt.example.searchtext.R;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;

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

    public static String errorHandleResponse(Throwable throwable, Context context) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
        if (throwable instanceof HttpException) {
            return context.getString(R.string.err_connection_lost);
        } else if (throwable instanceof IOException) {
            return context.getString(R.string.err_no_network);
        } else {
            return context.getString(R.string.err_connection_lost);
        }
    }
}
