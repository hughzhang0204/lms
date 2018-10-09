package com.hugo.LMS.exceptions;

public class LecturerNotFoundException extends RuntimeException {

    public LecturerNotFoundException() {
    }

    public LecturerNotFoundException(String message) {
        super(message);
    }
}
