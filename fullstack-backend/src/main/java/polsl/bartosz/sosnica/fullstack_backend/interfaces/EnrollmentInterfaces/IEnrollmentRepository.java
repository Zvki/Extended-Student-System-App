package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.user.ResponseUserGradeDTO;
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
         * Retrieves all enrollment details for a specific user.
         *
         * <p>
         * Each result contains the subject name, grade (or 0 if not set), and the
         * subject description.
         * </p>
         *
         * @param userId the ID of the user
         * @return a list of {@link ResponseEnrollmentDTO} objects
         */
        @Query("SELECT new polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO(" +
                        "e.subject.name, " +
                        "COALESCE(e.grade, 0), " +
                        "e.subject.description) " +
                        "FROM EnrollmentModel e WHERE e.user.id = :userId")
        List<ResponseEnrollmentDTO> findEnrollmentByUserId(@Param("userId") long userId);

        /**
         * Retrieves a specific enrollment by user ID and subject ID.
         *
         * @param userId    the ID of the user
         * @param subjectId the ID of the subject
         * @return the {@link EnrollmentModel} instance if found, otherwise {@code null}
         */
        @Query("SELECT e FROM EnrollmentModel e WHERE e.user.id = :userId AND e.subject.id = :subjectId")
        EnrollmentModel findByUserIdAndSubjectId(@Param("userId") Long userId, @Param("subjectId") Long subjectId);

        /**
         * Retrieves a list of users enrolled in a specific subject, along with their
         * grades.
         *
         * <p>
         * Each result includes the user's ID, name, surname, and assigned grade.
         * </p>
         *
         * @param subjectId the ID of the subject
         * @return a list of {@link ResponseUserGradeDTO} containing user and grade info
         */
        @Query("SELECT new polsl.bartosz.sosnica.fullstack_backend.dto.user.ResponseUserGradeDTO(u.id, u.name, u.surname, e.grade) "
                        +
                        "FROM UserModel u " +
                        "JOIN EnrollmentModel e ON e.user.id = u.id " +
                        "WHERE e.subject.id = :subjectId")
        List<ResponseUserGradeDTO> findUsersBySubjectId(@Param("subjectId") Long subjectId);

}
