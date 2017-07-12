package com.lhl.persistence;

import android.content.Context;

import com.lhl.persistence.cache.ICache;
import com.lhl.persistence.cache.LruAlgCache;
import com.lhl.persistence.log.DefaultLog;
import com.lhl.persistence.log.ILog;
import com.lhl.persistence.storage.AsyncStorage;
import com.lhl.persistence.storage.ExternalReadWriteLockFileStorage;
import com.lhl.persistence.storage.IStorage;
import com.lhl.persistence.utils.PersUtil;

/**
 * Created by jeremy on 2016/12/16.
 */

public class PersistenceBuilder {

    private Context context;
    private ICache cache;
    private IStorage storage;
    private ILog log;

    public PersistenceBuilder(Context context) {
        PersUtil.checkNull("Context", context);
        this.context = context;
    }

    public PersistenceBuilder setCache(ICache cache) {
        this.cache = cache;
        return this;
    }

    public PersistenceBuilder setLog(ILog log) {
        this.log = log;
        return this;
    }

    public PersistenceBuilder setStorage(IStorage storage) {
        this.storage = storage;
        return this;
    }

    ICache getCache() {
        if (cache == null) {
            cache = new LruAlgCache();
        }
        return cache;
    }

    IStorage getStorage() {
        if (storage == null) {
            storage = new AsyncStorage(new ExternalReadWriteLockFileStorage(context));
        }
        return storage;
    }

    ILog getLog() {
        if (log == null) {
            return new DefaultLog();
        }
        return log;
    }

    public void build() {
        Persistence.build(this);
    }
}
