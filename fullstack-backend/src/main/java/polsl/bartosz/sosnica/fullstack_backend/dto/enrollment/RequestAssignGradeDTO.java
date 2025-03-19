package polsl.bartosz.sosnica.fullstack_backend.dto.enrollment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestAssignGradeDTO {
    @NotEmpty(message = "userId cannot be empty")
    private Long userId;

    @NotEmpty(message = "subjectId cannot be empty")
    private Long subjectId;

    @NotEmpty(message = "grade cannot be empty")
    private Double grade;
}
