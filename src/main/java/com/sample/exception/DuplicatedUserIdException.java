package com.sample.exception;

public class DuplicatedUserIdException extends RuntimeException {

    public DuplicatedUserIdException(Throwable cause) {
        super(cause);
    }
}