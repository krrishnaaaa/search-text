package com.pcsalt.example.searchtext.utils;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.pcsalt.example.searchtext.BuildConfig;
import com.pcsalt.example.searchtext.R;

import java.io.EOFException;
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

    public static boolean isValidIp(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.IP_ADDRESS.matcher(target).matches();
        }
    }

    public static String errorHandleResponse(Throwable throwable, Context context) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
        if (throwable instanceof HttpException) {
            return context.getString(R.string.err_connection_lost);
        } else if (throwable instanceof EOFException) {
            return "Provide valid data";
        } else if (throwable instanceof IOException) {
            return context.getString(R.string.err_no_network);
        } else {
            return context.getString(R.string.err_connection_lost);
        }
    }

    public static void hideKeyboard(Context context, View textView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null && textView != null) {
            imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
        }
    }
}
