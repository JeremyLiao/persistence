package com.lhl.persistence.storage;

import android.text.TextUtils;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jeremy on 2016/12/16.
 */

public class FileStorage implements IStorage {

    private File storageHome;

    public FileStorage(File storageHome) {
        this.storageHome = storageHome;
        if (!storageHome.exists()) {
            storageHome.mkdirs();
        }
        if (!storageHome.exists()) {
            throw new RuntimeException("StorageHome not exist: " + storageHome.getAbsolutePath());
        }
        Log.d("Persistence", "StorageHome: " + storageHome.getAbsolutePath());
    }

    @Override
    public <T> boolean put(String key, T value) {
        if (TextUtils.isEmpty(key) || value == null) {
            return false;
        }
        return saveObject(new File(storageHome, key), value);
    }

    @Override
    public <T> T get(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return (T) getObject(new File(storageHome, key));
    }

    @Override
    public boolean delete(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        File file = new File(storageHome, key);
        return file.delete();
    }

    @Override
    public boolean deleteAll() {
        for (String key : getKeySet()) {
            delete(key);
        }
        return true;
    }

    @Override
    public long count() {
        return getKeySet().size();
    }

    @Override
    public boolean contains(String key) {
        return new File(storageHome, key).exists();
    }

    @Override
    public Set<String> getKeySet() {
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.exists();
            }
        };
        File[] files = storageHome.listFiles(fileFilter);
        Set<String> keySet = new HashSet<>();
        if (files != null && files.length > 0) {
            for (File file : files) {
                keySet.add(file.getName());
            }
        }
        return null;
    }

    private static boolean saveObject(File file, Object o) {
        if (file == null || o == null) {
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(o);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            safeClose(oos);
        }
    }

    private static Object getObject(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            safeClose(ois);
        }
    }

    private static void safeClose(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }
}
