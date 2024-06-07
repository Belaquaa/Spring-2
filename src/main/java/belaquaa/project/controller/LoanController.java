package belaquaa.project.controller;

import belaquaa.project.model.UserLoan;
import belaquaa.project.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;


    @GetMapping("/loan")
    public String getLoanAmount(@RequestParam Long userId, Model model) {

        if (userId == null || userId < 0) return "redirect:/error";

        double loanAmount = loanService.getApprovedLoanAmount(userId);
        UserLoan userLoan = loanService.getUserLoanByUserId(userId);

        if (userLoan == null) {
            userLoan = new UserLoan(userId);
        }
        model.addAttribute("userLoan", userLoan);
        model.addAttribute("loanAmount", loanAmount);
        return "loan";
    }
}
