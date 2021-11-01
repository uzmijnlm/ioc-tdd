package com.tdd.ioc;

public interface Binder {
    <T> BinderBuilder<T> bind(Class<T> aClass);


    interface BinderBuilder<T> {
        void byInstance(T preDefinedAddress);
        void scope(Scope prototype);
        void byCreator(Creator<T> creator);
    }
}
