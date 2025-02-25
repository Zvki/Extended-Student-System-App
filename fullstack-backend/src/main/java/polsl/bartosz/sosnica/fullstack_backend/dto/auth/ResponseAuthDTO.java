package polsl.bartosz.sosnica.fullstack_backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

@AllArgsConstructor
@Getter
@Setter
public class ResponseAuthDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String createdAt;
    private UserModel.Status status;
    private UserModel.Role role;

}
