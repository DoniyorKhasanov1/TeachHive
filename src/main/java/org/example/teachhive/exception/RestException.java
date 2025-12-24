package org.example.teachhive.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestException extends RuntimeException {
    private String errorMessage;
    private ErrorCodes codes;
    private String userMessage;

    public RestException(String errorMessage, ErrorCodes codes){
        this.errorMessage = errorMessage;
        this.codes = codes;
        this.userMessage = null;
    }
}
