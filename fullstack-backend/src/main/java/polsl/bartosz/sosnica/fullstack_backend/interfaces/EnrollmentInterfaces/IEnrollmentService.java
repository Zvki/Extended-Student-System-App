package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IEnrollmentService {

    EnrollmentModel enrollStudentToSubject(UserModel user, SubjectModel subject);
    
} 
