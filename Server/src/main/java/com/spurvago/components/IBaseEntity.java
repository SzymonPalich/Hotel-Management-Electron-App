package com.spurvago.components;

public interface IBaseEntity<T> {
    void map(T source);

    boolean validate();
}
