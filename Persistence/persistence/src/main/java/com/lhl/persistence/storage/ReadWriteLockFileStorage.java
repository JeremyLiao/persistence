package com.lhl.persistence.storage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jeremy on 2016/12/18.
 */

public class ReadWriteLockFileStorage extends FileStorage {

    private Map<String, ReadWriteLock> lockMap;

    public ReadWriteLockFileStorage(File storageHome) {
        super(storageHome);
        lockMap = new HashMap<>();
    }

    private ReadWriteLock getReadWriteLock(String key) {
        if (!lockMap.containsKey(key)) {
            synchronized (this) {
                if (!lockMap.containsKey(key)) {
                    lockMap.put(key, new ReentrantReadWriteLock());
                }
            }
        }
        return lockMap.get(key);
    }

    @Override
    public <T> boolean put(String key, T value) {
        ReadWriteLock readWriteLock = getReadWriteLock(key);
        try {
            readWriteLock.writeLock().lock();
            return super.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public <T> T get(String key) {
        ReadWriteLock readWriteLock = getReadWriteLock(key);
        try {
            readWriteLock.readLock().lock();
            return super.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public boolean delete(String key) {
        ReadWriteLock readWriteLock = getReadWriteLock(key);
        try {
            readWriteLock.writeLock().lock();
            return super.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
