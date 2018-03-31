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

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        Observable<?> o = (Observable<?>) other;

        return value == null ? o.value == null : value.equals(o.value);
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    private void notifyValueChanged() {
        for (Observer<T> observer : observers)
            observer.onChange(value);
    }

    public void addObserver(Observer<T> observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        this.observers.remove(observer);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;

        notifyValueChanged();
    }
}
