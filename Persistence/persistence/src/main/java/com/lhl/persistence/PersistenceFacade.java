package com.lhl.persistence;

import com.lhl.persistence.storage.IStorage;

import java.util.Set;

/**
 * Created by jeremy on 2016/12/16.
 */

public interface PersistenceFacade extends IStorage {

    boolean isBuilt();

    void destroy();

    class EmptyPersistenceFacade implements PersistenceFacade {
        @Override
        public <T> boolean put(String key, T value) {
            throwValidation();
            return false;
        }

        @Override
        public <T> T get(String key) {
            throwValidation();
            return null;
        }

        @Override
        public boolean delete(String key) {
            throwValidation();
            return false;
        }

        @Override
        public boolean deleteAll() {
            throwValidation();
            return false;
        }

        @Override
        public long count() {
            throwValidation();
            return 0;
        }

        @Override
        public boolean contains(String key) {
            throwValidation();
            return false;
        }

        @Override
        public boolean isBuilt() {
            throwValidation();
            return false;
        }

        @Override
        public void destroy() {
            throwValidation();
        }

        @Override
        public Set<String> getKeySet() {
            throwValidation();
            return null;
        }

        private void throwValidation() {
            throw new IllegalStateException("Persistence is not built. " +
                    "Please call build() and wait the initialisation finishes.");
        }
    }
}
