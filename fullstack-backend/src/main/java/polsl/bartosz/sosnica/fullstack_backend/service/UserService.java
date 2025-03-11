package polsl.bartosz.sosnica.fullstack_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserRepository;
import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserService;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

/**
 * Service class responsible for handling user-related operations.
 * Implements the {@link IUserService} interface.
 */
@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository the repository for user-related database operations
     */
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserModel> getUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public String findPasswordById(Long userId) {
        try {
            return userRepository.findPasswordById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Boolean updatePassword(Long userId, String newPassword) {
        try {
            userRepository.updatePassword(userId, newPassword);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean updateUser(Long userId, String name, String surname, String email) {
        try {
            userRepository.updateUser(userId, name, surname, email);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
