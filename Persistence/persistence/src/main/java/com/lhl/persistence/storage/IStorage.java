package com.lhl.persistence.storage;

import java.util.Set;

/**
 * Created by jeremy on 2016/12/16.
 */

public interface IStorage {

    <T> boolean put(String key, T value);

    <T> T get(String key);

    boolean delete(String key);

    boolean deleteAll();

    long count();

    boolean contains(String key);

    Set<String> getKeySet();
}
