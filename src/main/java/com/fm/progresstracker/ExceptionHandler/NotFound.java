package com.fm.progresstracker.ExceptionHandler;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
