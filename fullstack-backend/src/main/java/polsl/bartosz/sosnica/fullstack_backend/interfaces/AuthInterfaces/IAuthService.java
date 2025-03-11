package polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces;

import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;

public interface IAuthService {

    /**
     * Authenticates a user based on provided login credentials.
     *
     * @param loginData the login request containing email and password
     * @return a {@link ResponseAuthDTO} containing user details if authentication
     *         is successful, otherwise {@code null}
     */
    ResponseAuthDTO login(RequestLoginDTO loginData);

    /**
     * Registers a new user in the system.
     *
     * @param registerData the registration request containing user details
     * @return a {@link ResponseAuthDTO} with the created user details if
     *         registration is successful, otherwise {@code null}
     */
    ResponseAuthDTO register(RequestRegisterDTO registerData);

}
