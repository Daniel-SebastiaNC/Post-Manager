package dev.danielsebastian.postmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleDataNotFoundException(DataNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message: ", e.getMessage());
        response.put("error: ", "Data not found");

        return response;
    }

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleUsernameNotFoundException(UsernameOrPasswordInvalidException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message: ", e.getMessage());
        response.put("error: ", "Login failed");

        return response;
    }


}
