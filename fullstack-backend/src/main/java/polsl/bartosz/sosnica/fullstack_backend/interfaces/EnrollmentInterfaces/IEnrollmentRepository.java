package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO;
import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;

public interface IEnrollmentRepository extends JpaRepository<EnrollmentModel, Long> {
    
    boolean existsByUserIdAndSubjectId(Long userId, Long subjectId);

    @Query("SELECT new polsl.bartosz.sosnica.fullstack_backend.dto.enrollment.ResponseEnrollmentDTO(" +
    "e.subject.name, " +
    "COALESCE(e.grade, 0), " +
    "e.subject.description) " +
    "FROM EnrollmentModel e WHERE e.user.id = :userId")
    List<ResponseEnrollmentDTO> findEnrollmentByUserId(@Param("userId")long userId);

}
