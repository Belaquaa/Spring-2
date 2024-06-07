package belaquaa.project.service.loan;

import belaquaa.project.model.Car;
import belaquaa.project.model.UserLoan;
import belaquaa.project.service.user.UserRepository;
import belaquaa.IncomeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Service
public class LoanService {

    @Value("${loan.minimalIncome}")
    private double minimalIncome;

    @Value("${loan.maxAnnualLoanRatio}")
    private double maxAnnualLoanRatio;

    @Value("${loan.maxCarLoanRatio}")
    private double maxCarLoanRatio;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private IncomeClient incomeClient;

    @Autowired
    private UserRepository userRepository;

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
    public UserLoan getUserLoanByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(user -> loanRepository.findByUserId(user.getId()))
                .orElse(null);
    }
}