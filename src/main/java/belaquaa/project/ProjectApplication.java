package belaquaa.project;

import belaquaa.project.model.User;
import belaquaa.project.model.Car;
import belaquaa.project.service.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@ComponentScan("belaquaa")
public class ProjectApplication {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(ProjectApplication.class, args);
        UserService userService = context.getBean(UserService.class);

        List<Car> cars = List.of(
                new Car("Honda", 12, 102, 1_000_000),
                new Car("Mercedes", 13, 103, 2_000_000),
                new Car("Aurus", 14, 12, 23_000),
                new Car("Baurus", 15, 1123, 230_000),
                new Car("Caurus", 17, 122, 2_003_000),
                new Car("Daurus", 16, 10, 9_000_000),
                new Car("Faurus", 20, 156, 233_000),
                new Car("Gaurus", 32, 135, 243_000),
                new Car("Jaurus", 11, 15, 253_000),
                new Car("Haurus", 4, 1023, 239_000),
                new Car("Zaurus", 1, 2, 9_999_999)
        );

        AtomicInteger counter = new AtomicInteger(1);
        cars.forEach(car -> userService.addUser(new User(
                "User" + counter.get(),
                "Lastname" + counter.getAndIncrement(),
                "user" + counter.get() + "@mail.ru",
                car))
        );

        Optional<User> foundUser = userService.getUserByCarModelAndSeries("Honda", 102);
        System.out.println(foundUser.map(user -> " User found: " + user).orElse(" User not found"));
    }
}

