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

@RestController
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping("/usersavailablesubjects/{userId}")
    public ResponseEntity<?> findSubjectNotEnrolledByUser(@PathVariable Long userId){

        var result = subjectService.findSubjectsNotEnrolledByUser(userId);

        if(result == null){
            ApiResponse<Void> apiResponse = new ApiResponse<>(false, "No data provided", null, null );
            return ResponseEntity.badRequest().body(apiResponse);
        }

        var correctResponse = new ApiResponse<List<SubjectModel>>(true, "Provided subjects", result, null);

        return ResponseEntity.ok(correctResponse);

    }
    
    
}
