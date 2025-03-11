package polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

/**
 * Repository interface for user-related database operations.
 * Extends {@link JpaRepository} to provide standard CRUD functionality.
 */
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user
     * @return an {@link Optional} containing the user if found
     */
    Optional<UserModel> findById(Long id);

    /**
     * Retrieves the password of a user by their ID.
     *
     * @param userId the ID of the user
     * @return the password of the user
     */
    @Query("SELECT u.password FROM UserModel u WHERE u.id = :userId")
    String findPasswordById(@Param("userId") Long userId);

    /**
     * Updates the password of a user.
     *
     * @param userId      the ID of the user
     * @param newPassword the new password to be set
     */
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = :password WHERE u.id = :userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String newPassword);

    /**
     * Updates the user details.
     *
     * @param userId  the ID of the user
     * @param name    the new name of the user
     * @param surname the new surname of the user
     * @param email   the new email of the user
     */
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.name = :name, u.surname = :surname, u.email = :email WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("name") String name, @Param("surname") String surname,
            @Param("email") String email);

}
