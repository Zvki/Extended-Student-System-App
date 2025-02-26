package polsl.bartosz.sosnica.fullstack_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserService;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
}
