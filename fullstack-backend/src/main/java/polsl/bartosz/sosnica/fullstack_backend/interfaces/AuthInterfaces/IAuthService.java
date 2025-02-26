package polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces;

import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;

public interface IAuthService {

    ResponseAuthDTO login(RequestLoginDTO loginData);

    ResponseAuthDTO register(RequestRegisterDTO registerData);

}
