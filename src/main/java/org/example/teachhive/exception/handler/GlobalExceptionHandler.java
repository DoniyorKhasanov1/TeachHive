package org.example.teachhive.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.exception.ErrorCodes;
import org.example.teachhive.exception.RestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(RestException.class)
    public ResponseEntity<Map<String, Object>> handleRestException(RestException ex) {
        log.error("RestException: {}", ex.getErrorMessage());
        Map<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", ex.getUserMessage() != null ? ex.getUserMessage() : ex.getErrorMessage());
        body.put("errorCode", ex.getCodes().getMessage());
        body.put("status", ex.getCodes().getStatusCode());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity
                .status(ex.getCodes().getStatusCode())
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        Map<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", "Validation failed");
        body.put("errors", fieldErrors);
        body.put("errorCode", ErrorCodes.InvalidParams.getMessage());
        body.put("status", ErrorCodes.InvalidParams.getStatusCode());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity
                .status(ErrorCodes.InvalidParams.getStatusCode())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        log.error("Unexpected error", ex);
        Map<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", "Internal server error");
        body.put("errorCode", ErrorCodes.InternalServerError.getMessage());
        body.put("status", ErrorCodes.InternalServerError.getStatusCode());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity
                .status(ErrorCodes.InternalServerError.getStatusCode())
                .body(body);
    }
}


