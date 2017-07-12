package com.lhl.persistence.utils;

import android.text.TextUtils;

/**
 * Created by jeremy on 2016/12/16.
 */

public class PersUtil {

    public static void checkNull(String message, Object value) {
        if (value == null) {
            throw new NullPointerException(message + " should not be null");
        }
    }

    public static void checkNullOrEmpty(String message, String value) {
        if (TextUtils.isEmpty(value)) {
            throw new NullPointerException(message + " should not be null or empty");
        }
    }
}
