package com.centric.productapiservice.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private int code;
    private String message;

    public ErrorMessage(ErrorType errorType) {
        this(errorType, null);
    }

    public ErrorMessage(ErrorType errorType, String message) {

        super();
        this.code = errorType.getCode();
        // Override default errorType message if applicable
        if(message != null && !message.trim().isEmpty())
            this.message = message;
        else
            this.message = errorType.getMessage();
    }

    public ErrorMessage(String field, ErrorType errorType) {
        super();
        this.message = errorType.getMessage();
        this.code = errorType.getCode();
    }


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}