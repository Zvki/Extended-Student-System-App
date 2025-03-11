package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;

/**
 * Repository interface for managing enrollments in subjects.
 * Extends {@link JpaRepository} for basic CRUD operations.
 */
public interface IEnrollmentRepository extends JpaRepository<EnrollmentModel, Long> {

    /**
     * Checks if an enrollment exists for a given user and subject.
     *
     * @param userId    the ID of the user
     * @param subjectId the ID of the subject
     * @return true if the enrollment exists, false otherwise
     */
    boolean existsByUserIdAndSubjectId(Long userId, Long subjectId);

    /**
     * Retrieves enrollment details for a given user.
     *
     * @param userId the ID of the user
     * @return a list of enrollment details as {@link ResponseEnrollmentDTO}
     */
    @Query("SELECT new polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO(" +
            "e.subject.name, " +
            "COALESCE(e.grade, 0), " +
            "e.subject.description) " +
            "FROM EnrollmentModel e WHERE e.user.id = :userId")
    List<ResponseEnrollmentDTO> findEnrollmentByUserId(@Param("userId") long userId);

}
