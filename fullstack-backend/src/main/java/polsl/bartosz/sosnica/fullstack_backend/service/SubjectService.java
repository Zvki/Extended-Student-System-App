package polsl.bartosz.sosnica.fullstack_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.SubjectInterfaces.ISubjectService;
import polsl.bartosz.sosnica.fullstack_backend.model.SubjectModel;

@Service
public class SubjectService implements ISubjectService {

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectModel> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<SubjectModel> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
    
    public List<SubjectModel> findSubjectsNotEnrolledByUser(Long userId){
        try {
            return subjectRepository.findSubjectsNotEnrolledByUser(userId);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
