package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.net.http.HttpHeaders;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces.IAuthService;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.utils.JwtTokenUtil;
import polsl.bartosz.sosnica.fullstack_backend.utils.MyValidationUtils;

/**
 * REST controller responsible for handling user authentication operations,
 * including login, registration, authentication check, and logout.
 * 
 * <p>
 * This controller uses JWT tokens for stateless authentication and interacts
 * with the {@link IAuthService} to perform core authentication logic.
 * </p>
 * 
 * <p>
 * JWT tokens are set and managed using HTTP-only secure cookies.
 * </p>
 * 
 * @author Bartosz Sosnica
 */
@RestController
public class AuthController {

    @Autowired
    private Validator validator;

    private final IAuthService authService;

    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Constructs the {@code AuthController} with required dependencies.
     *
     * @param authService  authentication service for login and registration
     * @param jwtTokenUtil utility for handling JWT token generation and validation
     */
    @Autowired
    public AuthController(IAuthService authService, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Handles user login request.
     * <p>
     * Validates login data, authenticates the user, and issues a JWT token as a
     * secure cookie upon successful login.
     * </p>
     *
     * @param loginData login credentials (username and password)
     * @param response  HTTP response used to set the JWT cookie
     * @return a {@code ResponseEntity} with authentication result or validation
     *         errors
     */
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

        if (loginResult.getStatus().equals(UserModel.Status.Inactive)) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "User is not active", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        String jwtToken = jwtTokenUtil.generateToken(loginResult.getName());

        if (jwtToken == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Token generation failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        ResponseCookie cookie = ResponseCookie.from("authToken", jwtToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofMinutes(15))
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        var correctResponse = new ApiResponse<ResponseAuthDTO>(true, "Logged in", loginResult, null);

        return ResponseEntity.ok(correctResponse);
    }

    /**
     * Handles user registration request.
     * <p>
     * Validates user input and registers a new account via {@link IAuthService}.
     * </p>
     *
     * @param registerData data required to register a new user
     * @return a {@code ResponseEntity} with the registration result or validation
     *         errors
     */
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

    /**
     * Checks whether the user is currently authenticated.
     * 
     * <p>
     * Verifies if the request contains a valid JWT token cookie.
     * </p>
     *
     * @param request the HTTP servlet request to extract cookies
     * @return a {@code ResponseEntity} with a boolean flag indicating
     *         authentication status
     */
    @GetMapping("/checkauth")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        boolean isAuthenticated = false;

        if (request.getCookies() != null) {
            isAuthenticated = Arrays.stream(request.getCookies())
                    .anyMatch(cookie -> "authToken".equals(cookie.getName()));
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("authenticated", isAuthenticated);

        return ResponseEntity.ok(response);
    }

    /**
     * Logs the user out by invalidating the authentication cookie.
     * 
     * <p>
     * The JWT token is deleted from the client by setting a cookie with maxAge = 0.
     * </p>
     *
     * @param response HTTP response used to clear the authentication cookie
     * @return a {@code ResponseEntity} with a logout message
     */
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("authToken")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofMinutes(0))
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("U ve been logged out");
    }
}
