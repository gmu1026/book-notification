package net.cproduction.booknotification.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private ErrorDetail error;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = null;
    }

    public ApiResponse(String message, ErrorDetail error) {
        this.success = false;
        this.message = message;
        this.error = error;
    }
}
