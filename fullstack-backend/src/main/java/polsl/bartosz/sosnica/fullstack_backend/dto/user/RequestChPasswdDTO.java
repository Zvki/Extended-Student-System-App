package polsl.bartosz.sosnica.fullstack_backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RequestChPasswdDTO {

    private String currentPasswd;
    private String newPasswd;
    private String newPasswdConfirmation;

}