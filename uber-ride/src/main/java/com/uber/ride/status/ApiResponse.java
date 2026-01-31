package com.uber.ride.status;

import com.fasterxml.jackson.annotation.JsonInclude;

// Always include only non-null fields for cleaner JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private int statusCode;      // HTTP status code
    private boolean success;     // true/false
    private String message;      // descriptive message
    private Object data;         // optional payload

    // Constructors
    public ApiResponse() {}

    public ApiResponse(int statusCode, boolean success, String message) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
    }

    public ApiResponse(int statusCode, boolean success, String message, Object data) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
