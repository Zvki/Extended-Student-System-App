package polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);

}
