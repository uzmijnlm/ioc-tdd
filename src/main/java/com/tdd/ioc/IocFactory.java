package com.tdd.ioc;

import java.util.HashMap;
import java.util.Map;

public class IocFactory {

    private final Map<Class<?>, Object> map = new HashMap<>();

    public <T> void bind(Class<T> aClass) {
        try {
            T newInstance = aClass.newInstance();
            map.put(aClass, newInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> T getInstance(Class<T> aClass) {
        T instance = (T) map.get(aClass);
        if (instance == null) {
            throw new RuntimeException();
        } else {
            return (T) map.get(aClass);
        }
    }
}
