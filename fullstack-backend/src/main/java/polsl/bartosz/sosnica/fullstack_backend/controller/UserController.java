package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import polsl.bartosz.sosnica.fullstack_backend.dto.user.RequestChPasswdDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.user.RequestEditProfileDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserService;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.utils.MyValidationUtils;

@RestController
public class UserController {

    @Autowired
    private Validator validator;

    private IUserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PatchMapping("/changepasswd/{userId}")
    public ResponseEntity<?> ChangePasswd(@RequestBody RequestChPasswdDTO passwdChange, @PathVariable Long userId) {

        Set<ConstraintViolation<RequestChPasswdDTO>> violations = validator.validate(passwdChange);

        if (!violations.isEmpty()) {
            Map<String, String> errors = MyValidationUtils.extractValidationErrors(violations);
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Validation failed", null, errors);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var currentPasswd = userService.findPasswordById(userId);

        if (currentPasswd == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "User doesnt exist", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        if (!passwordEncoder.matches(passwdChange.getCurrentPasswd(), currentPasswd)) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Provided wrong current password", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        if (!passwdChange.getNewPasswd().equals(passwdChange.getNewPasswdConfirmation())) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "New password doesnt match", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        if (!userService.updatePassword(userId, passwordEncoder.encode(passwdChange.getNewPasswd()))) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Error occured while updating password", null,
                    null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        ApiResponse<Void> correctResponse = new ApiResponse<>(true, "Password updated", null, null);

        return ResponseEntity.ok(correctResponse);
    }

    @PatchMapping("/editprofile/{userId}")
    public ResponseEntity<?> EditProfile(@PathVariable Long userId, @RequestBody RequestEditProfileDTO editProfile) {

        Set<ConstraintViolation<RequestEditProfileDTO>> violations = validator.validate(editProfile);

        if (!violations.isEmpty()) {
            Map<String, String> errors = MyValidationUtils.extractValidationErrors(violations);
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Validation failed", null, errors);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var result = userService.updateUser(userId, editProfile.getName(), editProfile.getSurname(),
                editProfile.getEmail());

        if (result == false) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Error occured while updating user", null,
                    null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var userResponse = userService.getUserById(userId);

        if (userResponse.isEmpty()) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "User not found", null,
                    null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var user = userResponse.get();

        var correctResponse = new ApiResponse<UserModel>(true, "User changes saved", user, null);

        return ResponseEntity.ok(correctResponse);
    }

}
