package com.lhl.persistence;

import android.content.Context;

import com.lhl.persistence.utils.PersUtil;

import java.util.Set;

/**
 * Created by jeremy on 2016/12/16.
 */

final public class Persistence {

    private static PersistenceFacade persistenceFacade = new PersistenceFacade.EmptyPersistenceFacade();

    public static PersistenceBuilder init(Context context) {
        PersUtil.checkNull("Context", context);
        persistenceFacade = null;
        return new PersistenceBuilder(context.getApplicationContext());
    }

    static void build(PersistenceBuilder builder) {
        persistenceFacade = new DefaultPersistenceFacade(builder);
    }

    private Persistence() {
    }

    public static <T> boolean put(String key, T value) {
        return persistenceFacade.put(key, value);
    }

    public static <T> T get(String key) {
        return persistenceFacade.get(key);
    }

    public static boolean delete(String key) {
        return persistenceFacade.delete(key);
    }

    public static boolean deleteAll() {
        return persistenceFacade.deleteAll();
    }

    public static long count() {
        return persistenceFacade.count();
    }

    public static boolean contains(String key) {
        return persistenceFacade.contains(key);
    }

    public static boolean isBuilt() {
        return persistenceFacade.isBuilt();
    }

    public static void destroy() {
        persistenceFacade.destroy();
    }

    public static Set<String> getKeySet() {
        return persistenceFacade.getKeySet();
    }
}
