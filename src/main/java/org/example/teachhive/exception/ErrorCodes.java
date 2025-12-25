package org.example.teachhive.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    InvalidParams(400, "INVALID_PARAM"),
    BadRequest(400, "BAD_REQUEST"),
    Unauthorized(401, "UNAUTHORIZED"),
    Forbidden(403, "FORBIDDEN"),
    InvalidDevice(404, "INVALID_DEVICE"),
    NotFound(404, "NOT_FOUND"),
    InternalServerError(500, "INTERNAL_SERVER_ERROR"),
    AlreadyExists(409, "ALREADY_EXISTS"),
    INVALID_CREDENTIALS(407, "INVALID_CREDENTIALS")
    ;

    private final int statusCode;
    private final String message;
}
