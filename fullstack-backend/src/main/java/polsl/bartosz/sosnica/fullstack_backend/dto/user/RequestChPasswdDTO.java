package polsl.bartosz.sosnica.fullstack_backend.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RequestChPasswdDTO {

    @NotEmpty(message = "Old password cannot be empty")
    private String currentPasswd;

    @NotEmpty(message = "New password cannot be empty")
    private String newPasswd;

    @NotEmpty(message = "Confirmation cannot be empty")
    private String newPasswdConfirmation;

}