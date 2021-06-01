package com.fei.projetodecantadorbe.responseAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ApiExceptionResponse extends Exception {

    private final Logger logger = LoggerFactory.getLogger(ApiExceptionResponse.class);

    private static final long serialVersionUID = 1L;
    private ApiErrorResponse apiRetorno;

    public ApiExceptionResponse(Integer code, String message, String nativeMessage, String traceId, Exception ex) {
        super(nativeMessage, ex);
        logger.error(ex.getMessage(), ex);
        setApiRetorno(code, message, traceId);
    }

    public ApiExceptionResponse(Integer code, String message, String nativeMessage, String traceId) {
        super(nativeMessage, new Exception(nativeMessage));
        Exception ex = new Exception(nativeMessage);
        logger.error(ex.getMessage(), ex);
        setApiRetorno(code, message, traceId);
    }

    public ApiExceptionResponse(String msg, Exception ex) {
        super(msg, ex);
    }

    public ApiErrorResponse getApiRetorno() {
        return apiRetorno;
    }

    public void setApiRetorno(ApiErrorResponse apiRetorno) {
        this.apiRetorno = apiRetorno;
    }

    private void setApiRetorno(Integer code, String message, String traceId) {

        List<ErrorResponse> errors = new ArrayList<>();

        StringBuilder description = new StringBuilder();
        description.append(this.getMessage() + ": ");
        for (StackTraceElement var : this.getStackTrace()) {
            description.append(var.getFileName())
                    .append(" - ")
                    .append(var.getClassName())
                    .append(" (")
                    .append(var.getMethodName())
                    .append(":")
                    .append(var.getLineNumber())
                    .append(")");
        }

        errors.add(new ErrorResponse(String.valueOf(this.hashCode()),
                this.getMessage(), description.toString()));

        Throwable cause = this.getCause();
        while (cause != null) {
            StringBuilder description1 = new StringBuilder();
            description.append(this.getMessage() + ": ");
            for (StackTraceElement var : this.getStackTrace()) {
                description1.append(var.getFileName())
                        .append(" - ")
                        .append(var.getClassName())
                        .append(" (")
                        .append(var.getMethodName())
                        .append(":")
                        .append(var.getLineNumber())
                        .append(")\n");
            }

            errors.add(new ErrorResponse(String.valueOf(cause.hashCode()),
                    cause.getMessage(), description1.toString()));
            cause = cause.getCause();
        }

        apiRetorno = new ApiErrorResponse(String.valueOf(code), message,
                this.getMessage(), errors);
    }

}