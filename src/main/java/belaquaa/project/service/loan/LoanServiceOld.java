//package belaquaa.project.service.loan;
//
//import belaquaa.project.model.Car;
//import belaquaa.project.model.UserIncome;
//import belaquaa.project.service.user.UserRepository;
//import belaquaa.IncomeClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.Optional;
//
//@Service
//public class LoanService {
//
//    @Value("${loan.minimalIncome}")
//    private double minimalIncome;
//
//    @Value("${loan.maxAnnualLoanRatio}")
//    private double maxAnnualLoanRatio;
//
//    @Value("${loan.maxCarLoanRatio}")
//    private double maxCarLoanRatio;
//
//    @Value("${income.url}")
//    private String incomeURL;
//
//    @Autowired
//    private IncomeClient incomeClient;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public double getApprovedLoanAmount(Long userId) {
//        return userRepository.findById(userId)
//                .map(user -> {
//                    double annualIncome = getAnnualIncome(userId);
//                    double carPrice = Optional.ofNullable(user.getCar()).map(Car::getValue).orElse(0.0);
//
//                    if (annualIncome > minimalIncome * 12) {
//                        return Math.min(annualIncome * maxAnnualLoanRatio, carPrice * maxCarLoanRatio);
//                    } else if (carPrice > 1000000) {
//                        return carPrice * maxCarLoanRatio;
//                    }
//
//                    return 0.0;
//                })
//                .orElse(0.0);
//    }
//
//    private double getAnnualIncome(Long userId) {
//        try {
//            ResponseEntity<UserIncome[]> response = restTemplate.getForEntity(
//                    incomeURL + "?id=" + userId + "$", UserIncome[].class);
//            UserIncome[] incomes = response.getBody();
//            if (incomes != null && incomes.length > 0) {
//                System.out.println(incomes[0] + " " + userId);
//                return incomes[0].getIncome() * 12;
//            }
//            return 0;
//
//        } catch (HttpClientErrorException.NotFound e) {
//            System.err.println("User income not found for user with ID: " + userId);
//        } catch (HttpClientErrorException e) {
//            System.err.println("Failed to retrieve user income for user with ID: " + userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0.0;
//    }
//}