package ru.bolnik.springcourse.firstspringrestapp.Util;

import java.time.LocalDateTime;

public class PersonErrorResponse {
    private String message;
    private LocalDateTime timestamp;

    public PersonErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
