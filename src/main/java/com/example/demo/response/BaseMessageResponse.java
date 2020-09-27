package com.example.demo.response;

public class BaseMessageResponse {

    private Boolean status;
    private String message;

    public BaseMessageResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
