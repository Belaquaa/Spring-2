package belaquaa.project.controller;

import belaquaa.project.config.CarSortConfig;
import belaquaa.project.model.Car;
import belaquaa.project.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarSortConfig carSortConfig;

    @Value("${car.maxDisplayed}")
    private int maxCar;

    @GetMapping("/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "sortBy", required = false) String sortBy,
                          Model model) {
        List<Car> cars = carService.listCars();

        if (sortBy != null) {
            if (carSortConfig.getNonSortableFields().contains(sortBy)) return "redirect:/error";
            cars = sortCars(cars, sortBy);
        }

        int limit = Math.max(0, Optional.ofNullable(count).orElse(maxCar));
        cars = cars.subList(0, Math.min(limit, cars.size()));

        model.addAttribute("cars", cars);
        return "cars";
    }

    private List<Car> sortCars(List<Car> cars, String sortBy) {
        Comparator<Car> comparator = switch (sortBy) {
            case "model" -> Comparator.comparing(Car::getModel);
            case "year" -> Comparator.comparing(Car::getYear);
            case "series" -> Comparator.comparing(Car::getSeries);
            case "value" -> Comparator.comparing(Car::getValue);
            default -> null;
        };
        return comparator != null ? cars.stream().sorted(comparator).collect(Collectors.toList()) : cars;
    }
}
