package polsl.bartosz.sosnica.fullstack_backend.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

/**
 * Utility class for extracting validation errors from a set of constraint
 * violations.
 * 
 * <p>
 * This class provides a method to convert validation errors into a structured
 * map format,
 * where the keys are the property paths of the invalid fields, and the values
 * are the corresponding
 * validation messages.
 * </p>
 */
public class MyValidationUtils {

    /**
     * Extracts validation errors from a set of {@link ConstraintViolation} objects
     * and returns them as a map.
     * 
     * @param <T>        The type of the object being validated.
     * @param violations A set of constraint violations resulting from a validation
     *                   process.
     * @return A map containing field names as keys and validation messages as
     *         values.
     */
    public static <T> Map<String, String> extractValidationErrors(Set<ConstraintViolation<T>> violations) {

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errors;

    }

}
