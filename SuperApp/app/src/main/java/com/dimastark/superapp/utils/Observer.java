package com.dimastark.superapp.utils;

public interface Observer<T> {
    void update(T newValue);
}
