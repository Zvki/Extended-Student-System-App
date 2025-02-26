package polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

public interface ISubjectRepository extends JpaRepository<SubjectModel, Long> {
    
    Optional<SubjectModel> findById(Long id);

} 