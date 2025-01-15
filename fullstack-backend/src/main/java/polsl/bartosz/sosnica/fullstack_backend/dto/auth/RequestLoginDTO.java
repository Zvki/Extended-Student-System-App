package polsl.bartosz.sosnica.fullstack_backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestLoginDTO {

    private String username;

    @Email
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
