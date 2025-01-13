package polsl.bartosz.sosnica.fullstack_backend.dto.auth;

import lombok.*;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterDTO {

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4)
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;
}