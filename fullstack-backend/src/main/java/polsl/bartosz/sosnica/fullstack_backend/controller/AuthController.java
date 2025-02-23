package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.IAuthService;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.utils.JwtTokenUtil;
import polsl.bartosz.sosnica.fullstack_backend.utils.MyValidationUtils;

@RestController
public class AuthController {

    @Autowired
    private Validator validator;

    @Autowired
    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginDTO loginData, HttpServletResponse response) {

        Set<ConstraintViolation<RequestLoginDTO>> violations = validator.validate(loginData);

        if (!violations.isEmpty()) {
            Map<String, String> errors = MyValidationUtils.extractValidationErrors(violations);
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Validation failed", null, errors);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var loginResult = authService.login(loginData);

        if (loginResult == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Login failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        String jwtToken = JwtTokenUtil.generateToken(loginResult.getUsername());

        if (jwtToken == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Token generation failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        Cookie cookie = new Cookie("authToken", jwtToken);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);

        System.out.println("Cookie: " + cookie);

        var correctResponse = new ApiResponse<ResponseAuthDTO>(true, "Logged in", loginResult, null);

        return ResponseEntity.ok(correctResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestRegisterDTO registerData) {

        Set<ConstraintViolation<RequestRegisterDTO>> violations = validator.validate(registerData);

        if (!violations.isEmpty()) {
            Map<String, String> errors = MyValidationUtils.extractValidationErrors(violations);
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Validation failed", null, errors);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var registerResult = authService.register(registerData);

        if (registerResult == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Registration failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<ResponseAuthDTO>(true, "Registered", registerResult, null);

        return ResponseEntity.ok(correctResponse);
    }

}
