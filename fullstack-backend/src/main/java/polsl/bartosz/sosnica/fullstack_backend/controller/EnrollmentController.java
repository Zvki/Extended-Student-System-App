package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.RequestAssignGradeDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.user.ResponseUserGradeDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces.IEnrollmentService;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectService;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserService;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import java.util.List;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.utils.MyValidationUtils;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsible for managing student enrollments into subjects,
 * assigning grades, and retrieving enrollment-related data.
 *
 * <p>
 * This controller provides endpoints for:
 * <ul>
 * <li>Enrolling a student in a subject</li>
 * <li>Retrieving a student's enrollment list</li>
 * <li>Assigning grades to students</li>
 * <li>Getting enrolled users for a specific subject</li>
 * </ul>
 * </p>
 * 
 * @author Bartosz Sosnica
 */
@RestController
public class EnrollmentController {

    @Autowired
    private Validator validator;

    private IEnrollmentService enrollmentService;

    private IUserService userService;

    private ISubjectService subjectService;

    /**
     * Constructs an EnrollmentController with the required service dependencies.
     *
     * @param enrollmentService service for handling enrollment logic
     * @param userService       service for retrieving user data
     * @param subjectService    service for retrieving subject data
     */
    @Autowired
    public EnrollmentController(IEnrollmentService enrollmentService, IUserService userService,
            ISubjectService subjectService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.subjectService = subjectService;
    }

    /**
     * Enrolls a user in a specific subject.
     *
     * <p>
     * Performs validation to check if user and subject exist and whether
     * the user is already enrolled. If valid, creates a new enrollment.
     * </p>
     *
     * @param userId    the ID of the user
     * @param subjectId the ID of the subject
     * @return ResponseEntity containing result or error
     */
    @PostMapping("{userId}/enroll/{subjectId}")
    public ResponseEntity<?> enrollStudentToSubject(@PathVariable Long userId, @PathVariable Long subjectId) {

        Optional<UserModel> userOptional = userService.getUserById(userId);

        if (userOptional.isEmpty()) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "User doesn't exist", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<SubjectModel> subjectOptional = subjectService.getSubjectById(subjectId);

        if (subjectOptional.isEmpty()) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Subject doesn't exist", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        UserModel user = userOptional.get();
        SubjectModel subject = subjectOptional.get();

        if (enrollmentService.existsByUserIdAndSubjectId(user, subject)) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "User already enrolled", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        EnrollmentModel result = enrollmentService.enrollStudentToSubject(user, subject);

        if (result == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Enrollment failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<EnrollmentModel>(true, "Enrollments enrolled", result, null);

        return ResponseEntity.ok(correctResponse);
    }

    /**
     * Retrieves all subjects the specified user is enrolled in.
     *
     * @param userId ID of the user
     * @return ResponseEntity containing list of enrollments or error
     */
    @GetMapping("/getenrollments/{userId}")
    public ResponseEntity<?> findEnrollmentByUserId(@PathVariable Long userId) {

        var result = enrollmentService.findEnrollmentByUserId(userId);

        if (result == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "No data provided", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<List<ResponseEnrollmentDTO>>(true, "User Enrollments", result, null);

        return ResponseEntity.ok(correctResponse);
    }

    /**
     * Assigns a grade to a user for a specific subject enrollment.
     *
     * @param assignGradeParams data containing user ID, subject ID and grade
     * @return ResponseEntity containing result or error
     */
    @PatchMapping("/assigngrade")
    public ResponseEntity<?> assignGrade(@RequestBody RequestAssignGradeDTO assignGradeParams) {

        var response = enrollmentService.assignGrade(assignGradeParams);

        if (response == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Problem occured while adding grade", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<EnrollmentModel>(true, "User graded", response, null);

        return ResponseEntity.ok(correctResponse);
    }

    /**
     * Finds all users enrolled in a specific subject along with their grades.
     *
     * @param subjectId the ID of the subject
     * @return ResponseEntity containing list of users or error
     */
    @GetMapping("/findusersbysubject/{subjectId}")
    public ResponseEntity<?> findUserBySubjectId(@PathVariable Long subjectId) {

        var response = enrollmentService.findUsersBySubjectId(subjectId);

        if (response == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Problem occured while finding participants", null,
                    null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<List<ResponseUserGradeDTO>>(true, "Users enrolled", response, null);
        return ResponseEntity.ok(correctResponse);
    }

}
