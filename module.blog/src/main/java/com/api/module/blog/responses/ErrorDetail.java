package com.api.module.blog.responses;

public class ErrorDetail {
    private String field;
    private String message;

    public ErrorDetail() {
        super();
    }

    public ErrorDetail(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
