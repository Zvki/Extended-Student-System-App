package polsl.bartosz.sosnica.fullstack_backend.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.RequestAssignGradeDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.user.RequestChPasswdDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces.IEnrollmentRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces.IEnrollmentService;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.response.ApiResponse;
import polsl.bartosz.sosnica.fullstack_backend.utils.MyValidationUtils;

/**
 * Service class for managing enrollments of students in subjects.
 * Implements the {@link IEnrollmentService} interface.
 */
@Service
public class EnrollmentService implements IEnrollmentService {

    private IEnrollmentRepository enrollmentRepository;

    /**
     * Constructs an EnrollmentService with the given repository.
     *
     * @param enrollmentRepository the repository used for managing enrollments
     */
    @Autowired
    public EnrollmentService(IEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public EnrollmentModel enrollStudentToSubject(UserModel user, SubjectModel subject) {
        try {
            EnrollmentModel enrollment = new EnrollmentModel();
            enrollment.setUser(user);
            enrollment.setSubject(subject);
            return enrollmentRepository.save(enrollment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public boolean existsByUserIdAndSubjectId(UserModel user, SubjectModel subject) {
        try {
            return enrollmentRepository.existsByUserIdAndSubjectId(user.getId(), subject.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public List<ResponseEnrollmentDTO> findEnrollmentByUserId(long userId) {
        try {
            return enrollmentRepository.findEnrollmentByUserId(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public EnrollmentModel assignGrade(RequestAssignGradeDTO assignGradeParams) {

        try{
            EnrollmentModel enrollment = enrollmentRepository.findByUserIdAndSubjectId(assignGradeParams.getUserId(), assignGradeParams.getSubjectId());

            enrollment.setGrade(assignGradeParams.getGrade());
            enrollmentRepository.save(enrollment);
    
            return enrollment;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
