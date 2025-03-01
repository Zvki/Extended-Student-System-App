package polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

public interface ISubjectRepository extends JpaRepository<SubjectModel, Long> {
    
    Optional<SubjectModel> findById(Long id);

    @Query("SELECT s FROM SubjectModel s WHERE NOT EXISTS " +
    "(SELECT e FROM EnrollmentModel e WHERE e.subject.id = s.id AND e.user.id = :userId)")
    List<SubjectModel> findSubjectsNotEnrolledByUser(@Param("userId") Long userId);

} 