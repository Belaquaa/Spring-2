package belaquaa.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "yr", nullable = false)
    private int year;

    @Column(name = "series", nullable = false, unique = true)
    private int series;

    @Column(name = "value", nullable = false)
    private double value;

    public Car(String model, int year, int series, double value) {
        this.model = model;
        this.series = series;
        this.year = year;
        this.value = value;
    }
}
