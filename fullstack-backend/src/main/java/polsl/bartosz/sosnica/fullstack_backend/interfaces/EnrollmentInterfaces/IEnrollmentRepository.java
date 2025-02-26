package polsl.bartosz.sosnica.fullstack_backend.interfaces.EnrollmentInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import polsl.bartosz.sosnica.fullstack_backend.model.EnrollmentModel;

public interface IEnrollmentRepository extends JpaRepository<EnrollmentModel, Long> {
    

}
