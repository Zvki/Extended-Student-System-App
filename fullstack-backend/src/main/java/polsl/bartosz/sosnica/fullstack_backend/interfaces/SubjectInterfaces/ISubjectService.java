package polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces;

import java.util.List;
import java.util.Optional;

import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

public interface ISubjectService {

    List<SubjectModel> getAllSubjects();

    Optional<SubjectModel> getSubjectById(Long id);
    
}
