package com.dimastark.superapp.utils;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    private T value;
    private List<Observer<T>> observers;

    public Observable(T initialValue) {
        value = initialValue;
        observers = new ArrayList<>();
    }

    public void register(Observer<T> observer) {
        this.observers.add(observer);
    }

    public void unregister(Observer<T> observer) {
        this.observers.remove(observer);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;

        notifyValueChanged();
    }

    private void notifyValueChanged() {
        for (Observer<T> observer : observers)
            observer.update(value);
    }
}
