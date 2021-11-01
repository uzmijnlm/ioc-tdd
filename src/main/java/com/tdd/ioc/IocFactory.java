package com.tdd.ioc;

import java.util.HashMap;
import java.util.Map;

public class IocFactory {

    private final Map<Class<?>, Object> map = new HashMap<>();
    private final Map<Class<?>, Scope> scopeMap = new HashMap<>();

    public IocFactory() {

    }

    public IocFactory(Configurable configurable) {
        Binder binder = new Binder() {
            @Override
            public <T> BinderBuilder<T> bind(Class<T> aClass) {
                try {
                    map.put(aClass, aClass.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException();
                }
                scopeMap.put(aClass, Scope.SINGLETON);

                return new BinderBuilder<T>() {

                    @Override
                    public void byInstance(T preDefinedAddress) {
                        map.put(aClass, preDefinedAddress);
                    }

                    @Override
                    public void scope(Scope scope) {
                        scopeMap.put(aClass, scope);
                    }
                };
            }
        };
        configurable.configure(binder);
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
