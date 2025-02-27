package polsl.bartosz.sosnica.fullstack_backend.dto.enrollment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseEnrollmentDTO {
    
    private String subjectName;
    private double grade;
}
