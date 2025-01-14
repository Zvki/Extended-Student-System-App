package polsl.bartosz.sosnica.fullstack_backend.dto.auth;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ResponseRegisterDTO {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String createdAt;

}
