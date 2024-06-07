package belaquaa.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false)
    private final String model;

    @Column(name = "yr", nullable = false)
    private final int year;

    @Column(name = "series", nullable = false, unique = true)
    private final int series;

    @Column(name = "value", nullable = false)
    private final double value;

}
