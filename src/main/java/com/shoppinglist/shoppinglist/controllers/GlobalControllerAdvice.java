package com.shoppinglist.shoppinglist.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(@NonNull final MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex.fillInStackTrace());
        final var errors = ex.getBindingResult().getAllErrors().stream().collect(
            Collectors.toMap(
                // key
                error -> error instanceof FieldError fieldError ?
                    fieldError.getField() : error.getObjectName(),
                // value
                error -> Objects.requireNonNullElse(
                    error.getDefaultMessage(), "invalid")
            )
        );

        final var body = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST, "Validation failed");

        body.setProperty("errors", errors);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    ResponseEntity<ProblemDetail> handleConstraintViolationException(@NonNull final ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex.fillInStackTrace());
        final var errors = ex.getConstraintViolations().stream().collect(
            Collectors.toMap(
                // key
                ConstraintViolation::getPropertyPath,
                // value
                ConstraintViolation::getMessage
            )
        );

        final var body = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST, "validation failed");

        body.setProperty("errors", errors);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ProblemDetail> handleResourceDoesNotExistException(@NonNull final NoSuchElementException ex, @NonNull final HttpServletRequest request) {
        log.error(ex.getMessage(), ex.fillInStackTrace());
        final var body = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND, "The resource '" + request.getRequestURI() + "' does not exist");

        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<ProblemDetail> handleAll(@NonNull final Exception ex) {
        log.error(ex.getMessage(), ex.fillInStackTrace());
        final var body = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred");

        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
