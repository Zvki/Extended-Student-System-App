package polsl.bartosz.sosnica.fullstack_backend.interfaces.AuthInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IAuthRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);

}
