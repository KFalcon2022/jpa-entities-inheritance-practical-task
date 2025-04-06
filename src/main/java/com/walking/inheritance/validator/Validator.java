package com.walking.inheritance.validator;

public interface Validator<T> {
    void validate(T obj);
}
