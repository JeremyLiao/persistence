package com.lhl.persistence;

import com.lhl.persistence.cache.ICache;
import com.lhl.persistence.log.ILog;
import com.lhl.persistence.storage.IStorage;

import java.util.Set;

/**
 * Created by jeremy on 2016/12/16.
 */

public class DefaultPersistenceFacade implements PersistenceFacade {

    private PersistenceBuilder builder;
    private ICache cache;
    private IStorage storage;
    private ILog log;

    public DefaultPersistenceFacade(PersistenceBuilder builder) {
        this.builder = builder;
        cache = builder.getCache();
        storage = builder.getStorage();
        log = builder.getLog();
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public void destroy() {
    }

    @Override
    public <T> boolean put(final String key, final T value) {
        cache.put(key, value);
        storage.put(key, value);
        return true;
    }

    @Override
    public <T> T get(String key) {
        Object cachedObject = cache.get(key);
        if (cachedObject == null) {
            cachedObject = storage.get(key);
            if (cachedObject != null) {
                cache.put(key, cachedObject);
            }
        }
        return (T) cachedObject;
    }

    @Override
    public boolean delete(final String key) {
        cache.delete(key);
        storage.delete(key);
        return true;
    }

    @Override
    public boolean deleteAll() {
        cache.deleteAll();
        storage.deleteAll();
        return true;
    }

    @Override
    public long count() {
        return storage.count();
    }

    @Override
    public boolean contains(String key) {
        return storage.contains(key);
    }

    @Override
    public Set<String> getKeySet() {
        return storage.getKeySet();
    }
}
