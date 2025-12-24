package org.example.teachhive.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.exception.ErrorCodes;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Responding with unauthorized error. Message - {}", authException.getMessage());

        response.setStatus(SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message = (authException.getCause() != null) ? authException.getCause().getMessage() : authException.getMessage();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", Instant.ofEpochMilli(System.currentTimeMillis()).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        responseBody.put("errorCode", ErrorCodes.Unauthorized);
        responseBody.put("message", message);
        responseBody.put("userMessage", "You cannot log in, please authenticate.");
        responseBody.put("path", request.getRequestURI());

        response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(responseBody));
    }
}