package belaquaa.project.service.user;

import belaquaa.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series")
    Optional<User> findByCarModelAndSeries(@Param("model") String model, @Param("series") int series);
}
