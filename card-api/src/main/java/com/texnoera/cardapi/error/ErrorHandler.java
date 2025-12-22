package com.texnoera.cardapi.error;

import com.texnoera.cardapi.error.exceptions.CardNotFoundException;
import com.texnoera.cardapi.error.model.ErrorResponse;
import com.texnoera.cardapi.error.model.FieldErrorResponse;
import com.texnoera.cardapi.error.model.FieldValidationError;
import lombok.NonNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCardNotFoundException(CardNotFoundException ex, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        List<FieldValidationError> validations = ex.getFieldErrors().stream()
                .map(fieldError -> new FieldValidationError(fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .toList();
        FieldErrorResponse body = new FieldErrorResponse(
                "Invalid request param(eters)",
                new HashSet<>(validations));

        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

}
