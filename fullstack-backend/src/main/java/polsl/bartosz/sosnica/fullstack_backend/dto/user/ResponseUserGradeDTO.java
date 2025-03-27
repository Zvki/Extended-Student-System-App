package polsl.bartosz.sosnica.fullstack_backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUserGradeDTO {
    private Long id;
    private String name;
    private String surname;
}
