package belaquaa.project.service.loan;

import belaquaa.project.model.Car;
import belaquaa.project.service.user.UserRepository;
import belaquaa.IncomeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final IncomeClient incomeClient;
    private final UserRepository userRepository;

    @Value("${loan.minimalIncome}")
    private double minimalIncome;
    @Value("${loan.maxAnnualLoanRatio}")
    private double maxAnnualLoanRatio;
    @Value("${loan.maxCarLoanRatio}")
    private double maxCarLoanRatio;

    @Transactional(readOnly = true)
    public double getApprovedLoanAmount(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    double annualIncome = incomeClient.getAnnualIncome(userId);
                    double carPrice = Optional.ofNullable(user.getCar()).map(Car::getValue).orElse(0.0);

                    if (annualIncome > minimalIncome * 12) {
                        return Math.min(annualIncome * maxAnnualLoanRatio, carPrice * maxCarLoanRatio);
                    } else if (carPrice > 1000000) {
                        return carPrice * maxCarLoanRatio;
                    }

                    return 0.0;
                })
                .orElse(0.0);
    }
}