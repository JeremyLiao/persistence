package com.lhl.persistence.storage;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jeremy on 2016/12/19.
 */

public class AsyncStorage implements IStorage {

    private IStorage storage;
    private Executor executor;

    public AsyncStorage(IStorage storage) {
        this.storage = storage;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public <T> boolean put(final String key, final T value) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                storage.put(key, value);
            }
        });
        return true;
    }

    @Override
    public <T> T get(String key) {
        return storage.get(key);
    }

    @Override
    public boolean delete(final String key) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                storage.delete(key);
            }
        });
        return true;
    }

    @Override
    public boolean deleteAll() {
        return storage.deleteAll();
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
