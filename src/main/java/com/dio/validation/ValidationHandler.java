package com.dio.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(java.lang.Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(final Validation aValidation);

    List<java.lang.Error> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default java.lang.Error firstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().get(0);
        } else {
            return null;
        }
    }

    public interface Validation {
        void validate();
    }
}
