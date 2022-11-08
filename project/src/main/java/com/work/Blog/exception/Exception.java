package com.work.Blog.exception;

public abstract class Exception extends RuntimeException {

    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int statusCode();
}
