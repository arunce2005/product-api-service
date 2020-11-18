package com.centric.productapiservice.common.exception;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class ErrorResponse {
    private List<ErrorMessage> errors;
    private String requestMethod;
    private String path;

    public ErrorResponse(HttpServletRequest req, ErrorMessage errorMsg) {
        this(req, Collections.singletonList(errorMsg));
    }

    public ErrorResponse(HttpServletRequest req, List<ErrorMessage> errors) {
        this.requestMethod = req.getMethod();
        this.path = req.getRequestURI();
        this.errors = errors;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "requestMethod='" + requestMethod + '\'' +
                ", path='" + path + '\'' +
                ", errors=" + errors +
                '}';
    }
}
