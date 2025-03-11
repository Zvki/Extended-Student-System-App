package polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces;

import java.util.Optional;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IUserService {

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return an {@link Optional} containing the user if found, or {@code null} if
     *         an error occurs
     */
    Optional<UserModel> getUserById(Long id);

    /**
     * Finds the password of a user by their ID.
     *
     * @param userId the ID of the user
     * @return the password of the user, or {@code null} if an error occurs
     */
    String findPasswordById(Long userId);

    /**
     * Updates the password of a user.
     *
     * @param userId      the ID of the user
     * @param newPassword the new password to be set
     * @return {@code true} if the update is successful, {@code false} otherwise
     */
    Boolean updatePassword(Long userId, String newPassword);

    /**
     * Updates the user details.
     *
     * @param userId  the ID of the user
     * @param name    the new name of the user
     * @param surname the new surname of the user
     * @param email   the new email of the user
     * @return {@code true} if the update is successful, {@code false} otherwise
     */
    Boolean updateUser(Long userId, String name, String surname, String email);

}
