package com.lhl.persistence.cache;

import android.util.LruCache;

import java.util.Collection;
import java.util.Map;

/**
 * Created by jeremy on 2016/12/16.
 */

public class LruAlgCache implements ICache {

    private LruCache<String, Object> lruCache = null;
    private int cacheSize;

    public LruAlgCache() {
        this(Integer.MAX_VALUE);
    }

    public LruAlgCache(int cacheSize) {
        this.cacheSize = cacheSize;
        lruCache = new LruCache<String, Object>(cacheSize) {
            @Override
            protected int sizeOf(String key, Object value) {
                if (value instanceof Collection) {
                    return ((Collection) value).size();
                } else if (value instanceof Map) {
                    return ((Map) value).size();
                }
                return 1;
            }
        };
    }

    @Override
    public <T> boolean put(String key, T value) {
        lruCache.put(key, value);
        return true;
    }

    @Override
    public <T> T get(String key) {
        return (T) lruCache.get(key);
    }

    @Override
    public boolean delete(String key) {
        lruCache.remove(key);
        return true;
    }

    @Override
    public boolean deleteAll() {
        lruCache.evictAll();
        return true;
    }
}
