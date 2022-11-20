package com.work.Blog.exception;

public class InvalidRequest extends RuntimeException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public String fieldName;
    public String message;

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
