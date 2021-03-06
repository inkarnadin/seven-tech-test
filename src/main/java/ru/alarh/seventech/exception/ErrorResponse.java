package ru.alarh.seventech.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Error response class
 */
@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private Integer code;
    private String message;
    private List<String> errors;

}