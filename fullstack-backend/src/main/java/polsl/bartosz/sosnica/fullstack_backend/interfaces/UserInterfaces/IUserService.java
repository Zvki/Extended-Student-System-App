package polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces;

import java.util.Optional;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IUserService {
    
    Optional<UserModel> getUserById(Long id);

}
