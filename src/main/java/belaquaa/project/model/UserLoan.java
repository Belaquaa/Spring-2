package belaquaa.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_loans")
@Data
@NoArgsConstructor
public class UserLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "income", nullable = false)
    private double income;

    public UserLoan(Long userId) {
        this.userId = userId;
    }
}