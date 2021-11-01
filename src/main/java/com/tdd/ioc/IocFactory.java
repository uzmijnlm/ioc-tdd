package com.tdd.ioc;

import java.util.HashMap;
import java.util.Map;

public class IocFactory {

    private final Map<Class<?>, Object> map = new HashMap<>();
    private final Map<Class<?>, Scope> scopeMap = new HashMap<>();

    public <T> void bind(Class<T> aClass) {
        bind(aClass, Scope.SINGLETON);
    }

    public <T> void bind(Class<T> aClass, Scope scope) {
        if (scope == Scope.SINGLETON) {
            try {
                T newInstance = aClass.newInstance();
                map.put(aClass, newInstance);
                scopeMap.put(aClass, Scope.SINGLETON);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException();
            }
        } else {
            scopeMap.put(aClass, Scope.PROTOTYPE);
        }
    }

    public <T> T getInstance(Class<T> aClass) {
        Scope scope = scopeMap.get(aClass);
        if (scope == null) {
            throw new RuntimeException();
        } else if (scope == Scope.SINGLETON) {
            return (T) map.get(aClass);
        } else {
            try {
                return aClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException();
            }
        }
    }
}
