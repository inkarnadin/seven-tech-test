package ru.alarh.seventech.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for handling errors when executing requests.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Exception handling method for invalid input parameters
     *
     * @param e input exception
     * @return error response body
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(HttpStatus.BAD_REQUEST.value());

        List<String> errorList = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        response.setMessage("Input parameters error");
        response.setErrors(errorList);

        log.warn("Error during request: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handling method for custom business logic
     *
     * @param e input exception
     * @return error response body
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NegativeBalanceException.class, SameAccountCollisionException.class, MissingAccountException.class})
    public ResponseEntity<ErrorResponse> handleBusinessLogic(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        response.setErrors(new ArrayList<>());

        log.warn("Error during request: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}