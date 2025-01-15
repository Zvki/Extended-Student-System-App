package polsl.bartosz.sosnica.fullstack_backend.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class MyValidationUtils {

    public static <T> Map<String, String> extractValidationErrors(Set<ConstraintViolation<T>> violations) {

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errors;

    }

}
