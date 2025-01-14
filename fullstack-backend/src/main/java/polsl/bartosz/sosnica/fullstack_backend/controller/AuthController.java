package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private Validator validator;

    @Autowired
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestRegisterDTO registerData) {

        Set<ConstraintViolation<RequestRegisterDTO>> violations = validator.validate(registerData);

        if (!violations.isEmpty()) {

            Map<String, String> errors = new HashMap<>();
            for (ConstraintViolation<RequestRegisterDTO> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                String errorMessage = violation.getMessage();
                errors.put(fieldName, errorMessage);
            }

            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Validation failed", null, errors);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var registerResult = authService.register(registerData);

        if (registerResult == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Registration failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<ResponseRegisterDTO>(true, "Registered", registerResult, null);

        return ResponseEntity.ok(correctResponse);
    }

}
