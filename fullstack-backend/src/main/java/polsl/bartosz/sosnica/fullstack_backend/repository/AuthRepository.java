package polsl.bartosz.sosnica.fullstack_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

public interface AuthRepository extends JpaRepository<UserModel, Long> {

}
