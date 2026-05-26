package com.example.pncsegundoparcial00009123.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpaceNotFound.class)
    public ResponseEntity<ApiError> handleSpaceNotFound(SpaceNotFound spaceNotFound) {
        return new ResponseEntity<>(ApiError.builder()
                .timestamp(LocalDate.now())
                .code(HttpStatus.NOT_FOUND.value())
                .message(spaceNotFound.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpaceCannotBeDeletedException.class)
    public ResponseEntity<ApiError> handleSpaceCannotBeDeleted(SpaceCannotBeDeletedException spaceCannotBeDeleted) {
        return new ResponseEntity<>(ApiError.builder()
                .timestamp(LocalDate.now())
                .code(HttpStatus.CONFLICT.value())
                .message(spaceCannotBeDeleted.getMessage())
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException illegalArgument) {
        return new ResponseEntity<>(ApiError.builder()
                .timestamp(LocalDate.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(illegalArgument.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors().stream().collect(
                        java.util.stream.Collectors.toMap(
                                org.springframework.validation.FieldError::getField,
                                fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "Invalid value"
                        )
                );

        return new ResponseEntity<>(
                ApiError.builder()
                        .timestamp(LocalDate.now())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Validation failed")
                        .errors(errors)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
