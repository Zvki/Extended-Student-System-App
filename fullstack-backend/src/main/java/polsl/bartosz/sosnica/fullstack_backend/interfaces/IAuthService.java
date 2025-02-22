package polsl.bartosz.sosnica.fullstack_backend.interfaces;

import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;

public interface IAuthService {

    public ResponseAuthDTO login(RequestLoginDTO loginData);

    public ResponseAuthDTO register(RequestRegisterDTO registerData);

}
