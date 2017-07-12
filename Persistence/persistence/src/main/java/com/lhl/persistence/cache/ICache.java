package com.lhl.persistence.cache;

/**
 * Created by jeremy on 2016/12/16.
 */

public interface ICache {

    <T> boolean put(String key, T value);

    <T> T get(String key);

    boolean delete(String key);

    boolean deleteAll();
}
