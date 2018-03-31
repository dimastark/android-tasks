package com.dimastark.superapp.utils;

public interface Observer<T> {
    void onChange(T newValue);
}
