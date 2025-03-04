package polsl.bartosz.sosnica.fullstack_backend.service;

import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestLoginDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseAuthDTO;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces.IAuthRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces.IAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

@Service
public class AuthService implements IAuthService {

    @Autowired
    IAuthRepository authRepository;

    @Autowired
    public AuthService(IAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public ResponseAuthDTO login(RequestLoginDTO loginData) {

        UserModel user = authRepository.findByEmail(loginData.getEmail());

        if (user == null) {
            return null;
        }

        BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!BCryptPasswordEncoder.matches(loginData.getPassword(), user.getPassword())) {
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

    }

    public ResponseAuthDTO register(RequestRegisterDTO registerData) {

        if (!registerData.getPassword().equals(registerData.getPasswordConfirmation())) {
            return null;
        }

        BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();

        UserModel newUser = new UserModel(BCryptPasswordEncoder.encode(registerData.getPassword()),
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
    }

}
