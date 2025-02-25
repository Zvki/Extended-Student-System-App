package polsl.bartosz.sosnica.fullstack_backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterDTO {

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4)
    private String password;

    @NotEmpty(message = "Password confirmation cannot be empty")
    @Size(min = 4)
    private String passwordConfirmation;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;
}