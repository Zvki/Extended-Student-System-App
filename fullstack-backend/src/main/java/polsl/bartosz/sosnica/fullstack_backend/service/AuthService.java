package polsl.bartosz.sosnica.fullstack_backend.service;

import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces.IAuthRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces.IAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

/**
 * Service class responsible for handling authentication-related operations.
 * Implements the {@link IAuthService} interface.
 */
@Service
public class AuthService implements IAuthService {

    private IAuthRepository authRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor for AuthService.
     *
     * @param authRepository  the repository for authentication-related database
     *                        operations
     * @param passwordEncoder the encoder used for password hashing
     */
    @Autowired
    public AuthService(IAuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseAuthDTO login(RequestLoginDTO loginData) {
        try {
            UserModel user = authRepository.findByEmail(loginData.getEmail());

            if (user == null) {
                return null;
            }

            if (!passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                return null;
            }

            ResponseAuthDTO responseLoginDTO = new ResponseAuthDTO(
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getCreatedAt().toString(),
                    user.getStatus(),
                    user.getRole());

            return responseLoginDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public ResponseAuthDTO register(RequestRegisterDTO registerData) {

        try {
            if (!registerData.getPassword().equals(registerData.getPasswordConfirmation())) {
                return null;
            }
            UserModel newUser = new UserModel(passwordEncoder.encode(registerData.getPassword()),
                    registerData.getName(),
                    registerData.getSurname(),
                    registerData.getEmail());

            UserModel result = authRepository.save(newUser);

            ResponseAuthDTO responseRegisterDTO = new ResponseAuthDTO(
                    result.getId(),
                    result.getName(),
                    result.getSurname(),
                    result.getEmail(),
                    result.getCreatedAt().toString(),
                    result.getStatus(),
                    result.getRole());

            return responseRegisterDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
