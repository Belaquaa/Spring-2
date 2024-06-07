package belaquaa.project.service.loan;

import belaquaa.project.model.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<UserLoan, Long> {
}
