package polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

/**
 * Repository interface for subject-related database operations.
 * Extends {@link JpaRepository} to provide standard CRUD functionality.
 */
public interface ISubjectRepository extends JpaRepository<SubjectModel, Long> {

    /**
     * Finds a subject by its ID.
     *
     * @param id the ID of the subject
     * @return an {@link Optional} containing the subject if found
     */
    Optional<SubjectModel> findById(Long id);

    /**
     * Retrieves a list of subjects that a user is not enrolled in.
     *
     * @param userId the ID of the user
     * @return a list of subjects not enrolled by the user
     */
    @Query("SELECT s FROM SubjectModel s WHERE NOT EXISTS " +
            "(SELECT e FROM EnrollmentModel e WHERE e.subject.id = s.id AND e.user.id = :userId)")
    List<SubjectModel> findSubjectsNotEnrolledByUser(@Param("userId") Long userId);

}