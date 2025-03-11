package polsl.bartosz.sosnica.fullstack_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectService;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

/**
 * Service class responsible for handling subject-related operations.
 * Implements the {@link ISubjectService} interface.
 */
@Service
public class SubjectService implements ISubjectService {

    private ISubjectRepository subjectRepository;

    /**
     * Constructor for SubjectService.
     *
     * @param subjectRepository the repository for subject-related database
     *                          operations
     */
    @Autowired
    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectModel> getAllSubjects() {
        try {
            return subjectRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Optional<SubjectModel> getSubjectById(Long id) {
        try {
            return subjectRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<SubjectModel> findSubjectsNotEnrolledByUser(Long userId) {
        try {
            return subjectRepository.findSubjectsNotEnrolledByUser(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
