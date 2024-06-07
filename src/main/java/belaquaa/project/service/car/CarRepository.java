package belaquaa.project.service.car;

import belaquaa.project.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query("SELECT c FROM Car c WHERE c.id = (SELECT u.car.id FROM User u WHERE u.id = :userId)")
    Optional<Car> findCarByUserId(@Param("userId") String userId);
}
