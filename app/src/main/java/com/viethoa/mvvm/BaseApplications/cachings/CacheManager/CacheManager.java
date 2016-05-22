package com.viethoa.mvvm.BaseApplications.cachings.CacheManager;

import java.lang.reflect.Type;

/**
 * Created by VietHoa on 17/01/16.
 */
public interface CacheManager {

    void putString(String key, String value);

    void put(String key, Object object);

    String getString(String key);

    <T> T get(String key, Class<T> clazz);

    <T> T get(String key, Type type);
}
