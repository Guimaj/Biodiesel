package com.fei.projetodecantadorbe.controller.advice;

import com.fei.projetodecantadorbe.responseAPI.ApiErrorResponse;
import com.fei.projetodecantadorbe.responseAPI.ApiExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.HttpResource;

@Slf4j
@RestControllerAdvice
public class DefaultControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ApiExceptionResponse.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiExceptionResponse ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<ApiErrorResponse>(ex.getApiRetorno(), HttpStatus.valueOf(Integer.parseInt(ex.getApiRetorno().getCode())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getLocalizedMessage(), "", ex);
        return new ResponseEntity<ApiErrorResponse>(apiExceptionResponse.getApiRetorno(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleInternalError(HttpRequest req, HttpResource res, Exception ex) {
        logger.error(ex.getMessage(), ex);
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getLocalizedMessage(), "", ex);
        return new ResponseEntity<ApiErrorResponse>(apiExceptionResponse.getApiRetorno(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(HttpRequest req, HttpResource res, HttpClientErrorException.BadRequest ex) {
        logger.error(ex.getMessage(), ex);
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST", ex.getResponseBodyAsString(), "", ex);
        return new ResponseEntity<ApiErrorResponse>(apiExceptionResponse.getApiRetorno(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleException(HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);
        ApiExceptionResponse apiException = new ApiExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getLocalizedMessage(), "", ex);
        return new ResponseEntity<ApiErrorResponse>(apiException.getApiRetorno(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
