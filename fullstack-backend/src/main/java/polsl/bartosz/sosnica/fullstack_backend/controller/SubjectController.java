package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectService;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.service.SubjectService;

/**
 * Controller responsible for handling subject-related operations.
 * 
 * <p>
 * This controller provides endpoints for retrieving subjects that are not yet
 * enrolled by a specific user.
 * </p>
 * 
 * @author Bartosz Sosnica
 */
@RestController
public class SubjectController {

    private ISubjectService subjectService;

    /**
     * Constructs a SubjectController with the necessary service dependency.
     * 
     * @param subjectService the service managing subject-related operations
     */
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Retrieves a list of subjects that a given user is not enrolled in.
     * 
     * <p>
     * This method fetches all subjects available for a user to enroll in, excluding
     * those the user is already enrolled in.
     * </p>
     * 
     * @param userId the ID of the user for whom available subjects are being
     *               retrieved
     * @return a {@code ResponseEntity} containing a list of available subjects or
     *         an error message
     */
    @GetMapping("/usersavailablesubjects/{userId}")
    public ResponseEntity<?> findSubjectNotEnrolledByUser(@PathVariable Long userId) {

        var result = subjectService.findSubjectsNotEnrolledByUser(userId);

        if (result == null) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "No data provided", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<List<SubjectModel>>(true, "Provided subjects", result, null);

        return ResponseEntity.ok(correctResponse);

    }

}
