package polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);

    @Query("SELECT u.password FROM UserModel u WHERE u.id = :userId")
    String findPasswordById(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = :password WHERE u.id = :userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.name = :name, u.surname = :surname, u.email = :email WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("name") String name, @Param("surname") String surname,
            @Param("email") String email);

}
