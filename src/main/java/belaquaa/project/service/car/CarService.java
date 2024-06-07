package belaquaa.project.service.car;

import belaquaa.project.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Transactional(readOnly = true)
    public List<Car> listCars() {
        return carRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Car> getCarByUserId(String userId) {
        return carRepository.findCarByUserId(userId);
    }
}
