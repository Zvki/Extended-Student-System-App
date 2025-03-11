package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import java.util.List;

import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IEnrollmentService {

    /**
     * Enrolls a student in a subject.
     *
     * @param user    the user to be enrolled
     * @param subject the subject to enroll in
     * @return the created {@link EnrollmentModel} instance or {@code null} if an
     *         error occurs
     */
    EnrollmentModel enrollStudentToSubject(UserModel user, SubjectModel subject);

    /**
     * Checks if a student is already enrolled in a subject.
     *
     * @param user    the user to check
     * @param subject the subject to check
     * @return {@code true} if the enrollment exists, {@code false} otherwise
     */
    boolean existsByUserIdAndSubjectId(UserModel user, SubjectModel subject);

    /**
     * Finds all enrollments for a given user ID.
     *
     * @param userId the ID of the user
     * @return a list of {@link ResponseEnrollmentDTO} or {@code null} if an error
     *         occurs
     */
    List<ResponseEnrollmentDTO> findEnrollmentByUserId(long userId);

}
