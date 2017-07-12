package com.lhl.persistence.storage;

import android.content.Context;

/**
 * Created by hailiangliao on 2016/12/19.
 */

public class ExternalReadWriteLockFileStorage extends ReadWriteLockFileStorage {

    private static final String DEFAULT_HOME_NAME = "persistence";

    public ExternalReadWriteLockFileStorage(Context context) {
        this(context, DEFAULT_HOME_NAME);
    }

    public ExternalReadWriteLockFileStorage(Context context, String homeName) {
        super(context.getExternalFilesDir(homeName));
    }
}
