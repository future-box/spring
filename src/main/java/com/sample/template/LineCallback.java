package com.sample.template;

@FunctionalInterface
public interface LineCallback<T> {

    T compute(String line, T sum);
}
