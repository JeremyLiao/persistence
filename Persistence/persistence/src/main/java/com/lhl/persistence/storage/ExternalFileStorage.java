package com.lhl.persistence.storage;

import android.content.Context;

/**
 * Created by jeremy on 2016/12/16.
 */

public class ExternalFileStorage extends FileStorage {

    private static final String DEFAULT_HOME_NAME = "persistence";

    public ExternalFileStorage(Context context) {
        this(context, DEFAULT_HOME_NAME);
    }

    public ExternalFileStorage(Context context, String homeName) {
        super(context.getExternalFilesDir(homeName));
    }
}
