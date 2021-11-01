package com.tdd.ioc;

import java.util.HashMap;
import java.util.Map;

public class IocFactory {

    private final Map<Class<?>, Creator<?>> map = new HashMap<>();

    public IocFactory() {

    }

    public IocFactory(Configurable configurable) {
        Binder binder = new Binder() {
            @Override
            public <T> BinderBuilder<T> bind(Class<T> aClass) {
                try {
                    T newInstance = aClass.newInstance();
                    map.put(aClass, (Creator<T>) () -> newInstance);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException();
                }

                return new BinderBuilder<T>() {

                    @Override
                    public void byInstance(T preDefinedAddress) {
                        map.put(aClass, (Creator<T>) () -> preDefinedAddress);
                    }

                    @Override
                    public void scope(Scope scope) {
                        if (scope == Scope.PROTOTYPE) {
                            map.put(aClass, (Creator<T>) () -> {
                                try {
                                    return aClass.newInstance();
                                } catch (InstantiationException | IllegalAccessException e) {
                                    throw new RuntimeException();
                                }
                            });
                        }
                    }

                    @Override
                    public void byCreator(Creator<T> creator) {
                        map.put(aClass, creator);
                    }
                };
            }
        };
        configurable.configure(binder);
    }

    public <T> T getInstance(Class<T> aClass) {
        return (T) map.get(aClass).get();
    }
}
