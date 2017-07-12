package com.lhl.persistence.log;

import android.util.Log;

/**
 * Created by jeremy on 2016/12/16.
 */

public class DefaultLog implements ILog {

    @Override
    public void log(String message) {
        Log.d("Persistence", message);
    }
}
