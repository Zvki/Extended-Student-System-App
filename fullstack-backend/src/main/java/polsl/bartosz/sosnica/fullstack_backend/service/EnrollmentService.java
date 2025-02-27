package polsl.bartosz.sosnica.fullstack_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces.IEnrollmentRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces.IEnrollmentService;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

@Service
public class EnrollmentService implements IEnrollmentService {

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(IEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    
    public EnrollmentModel enrollStudentToSubject(UserModel user, SubjectModel subject) {
        EnrollmentModel enrollment = new EnrollmentModel();
        enrollment.setUser(user);
        enrollment.setSubject(subject);
        return enrollmentRepository.save(enrollment);
    }

    public boolean existsByUserIdAndSubjectId(UserModel user, SubjectModel subject ){
        return enrollmentRepository.existsByUserIdAndSubjectId(user.getId(), subject.getId());
    }

    public List<ResponseEnrollmentDTO> findEnrollmentByUserId(long userId){
        try{
            return enrollmentRepository.findEnrollmentByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
