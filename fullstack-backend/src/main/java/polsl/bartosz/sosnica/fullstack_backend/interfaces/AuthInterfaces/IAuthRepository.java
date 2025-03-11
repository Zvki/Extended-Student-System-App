package polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

/**
 * Repository interface for authentication-related database operations.
 * Extends {@link JpaRepository} to provide standard CRUD functionality.
 */
public interface IAuthRepository extends JpaRepository<UserModel, Long> {

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return the {@link UserModel} associated with the given email
     */
    UserModel findByEmail(String email);

}
