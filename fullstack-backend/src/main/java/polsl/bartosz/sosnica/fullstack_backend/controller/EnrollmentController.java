package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces.IEnrollmentService;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectService;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserService;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;

@RestController
public class EnrollmentController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    public EnrollmentController(IEnrollmentService enrollmentService, IUserService userService, ISubjectService subjectService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.subjectService = subjectService;
    }

    @PostMapping("{userId}/enroll/{subjectId}")
    public ResponseEntity<?> enrollStudentToSubject(@PathVariable Long userId, @PathVariable Long subjectId) {

        Optional<UserModel> userOptional = userService.getUserById(userId);
        
        if(userOptional.isEmpty()){
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "User doesn't exist", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<SubjectModel> subjectOptional = subjectService.getSubjectById(subjectId);
         
        if(subjectOptional.isEmpty()){
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Subject doesn't exist", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        UserModel user = userOptional.get();
        System.out.println(user.getName());
        SubjectModel subject = subjectOptional.get();
        System.out.println(subject.getName());

        EnrollmentModel result = enrollmentService.enrollStudentToSubject(user, subject);

        if(result == null){
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "Enrollment failed", null, null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<EnrollmentModel>(true, "Enrollment successful", result, null);

        return ResponseEntity.ok(correctResponse);
    }

    
}
