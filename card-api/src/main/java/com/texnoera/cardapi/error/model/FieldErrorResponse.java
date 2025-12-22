package com.texnoera.cardapi.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorResponse {

    private String message;
    private Set<FieldValidationError> fields;

}
