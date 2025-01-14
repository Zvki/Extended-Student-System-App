package polsl.bartosz.sosnica.fullstack_backend.service;

import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.dto.auth.RequestRegisterDTO;
import polsl.bartosz.sosnica.fullstack_backend.dto.auth.ResponseRegisterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;
import polsl.bartosz.sosnica.fullstack_backend.repository.AuthRepository;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public ResponseRegisterDTO register(RequestRegisterDTO registerData) {

        if (!registerData.getPassword().equals(registerData.getPasswordConfirmation())) {
            return null;
        }

        BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();

        UserModel newUser = new UserModel(registerData.getUsername(),
                BCryptPasswordEncoder.encode(registerData.getPassword()),
                registerData.getName(),
                registerData.getSurname(),
                registerData.getEmail());

        UserModel result = authRepository.save(newUser);

        if (result == null) {
            return null;
        }

        ResponseRegisterDTO responseRegisterDTO = new ResponseRegisterDTO(
                result.getId(),
                result.getUsername(),
                result.getName(),
                result.getSurname(),
                result.getEmail(),
                result.getCreatedAt().toString());

        return responseRegisterDTO;
    }

}
