package polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces;

import java.util.List;
import java.util.Optional;

import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

public interface ISubjectService {

    /**
     * Retrieves all subjects from the database.
     *
     * @return a list of {@link SubjectModel} containing all subjects, or
     *         {@code null} if an error occurs
     */
    List<SubjectModel> getAllSubjects();

    /**
     * Retrieves a subject by its ID.
     *
     * @param id the ID of the subject
     * @return an {@link Optional} containing the subject if found, or {@code null}
     *         if an error occurs
     */
    Optional<SubjectModel> getSubjectById(Long id);

    /**
     * Finds subjects that a user is not enrolled in.
     *
     * @param userId the ID of the user
     * @return a list of {@link SubjectModel} containing subjects the user has not
     *         enrolled in, or {@code null} if an error occurs
     */
    List<SubjectModel> findSubjectsNotEnrolledByUser(Long userId);

}
