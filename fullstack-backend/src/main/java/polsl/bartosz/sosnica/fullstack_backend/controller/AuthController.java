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
import polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces.IAuthService;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.utils.JwtTokenUtil;
import polsl.bartosz.sosnica.fullstack_backend.utils.MyValidationUtils;

/**
 * Controller responsible for handling authentication-related operations,
 * including user login and registration.
 * 
 * <p>
 * This controller provides endpoints for user authentication using JWT tokens.
 * It validates input data, processes authentication logic through the
 * {@code IAuthService} interface, and responds with appropriate messages.
 * </p>
 *
 * @author Bartosz Sosnica
 */
@RestController
public class AuthController {

    @Autowired
    private Validator validator;

    private final IAuthService authService;

    /**
     * Constructs an AuthController with the specified authentication service.
     * 
     * @param authService the authentication service implementation
     */
    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    /**
     * Handles user login requests.
     * 
     * <p>
     * This method validates user input, attempts authentication through the
     * authentication service, and generates a JWT token if the login is successful.
     * If authentication fails, an appropriate response is returned.
     * </p>
     * 
     * @param loginData the login request data containing username and password
     * @param response  the HTTP servlet response, used to set authentication cookies
     * @return a {@code ResponseEntity} containing authentication result or validation errors
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

        String jwtToken = JwtTokenUtil.generateToken(loginResult.getName());

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

    /**
     * Handles user registration requests.
     * 
     * <p>
     * This method validates user input, attempts to register a new user through the
     * authentication service, and returns an appropriate response. If registration
     * is successful, a success message is returned.
     * </p>
     * 
     * @param registerData the registration request data containing user details
     * @return a {@code ResponseEntity} containing registration result or validation errors
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

}
