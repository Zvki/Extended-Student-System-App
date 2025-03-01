package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import java.util.List;

import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IEnrollmentService {

    EnrollmentModel enrollStudentToSubject(UserModel user, SubjectModel subject);

    boolean existsByUserIdAndSubjectId(UserModel user, SubjectModel subject);

    List<ResponseEnrollmentDTO> findEnrollmentByUserId(long userId);
    
} 
