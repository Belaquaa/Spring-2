package belaquaa.project.controller;

import belaquaa.project.model.UserLoan;
import belaquaa.project.service.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/loan")
    public String getLoanAmount(@RequestParam Long userId, Model model) {

        if (userId == null || userId < 0) return "redirect:/error";

        double loanAmount = loanService.getApprovedLoanAmount(userId);

        model.addAttribute("userLoan", new UserLoan(userId));
        model.addAttribute("loanAmount", loanAmount);
        return "loan";
    }
}
