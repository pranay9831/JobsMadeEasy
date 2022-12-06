package Group3.JobsMadeEasy.authentication.applicant.controller.login;

import Group3.JobsMadeEasy.authentication.applicant.exception.ApplicantAuthenticationException;
import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import Group3.JobsMadeEasy.authentication.applicant.model.Login;
import Group3.JobsMadeEasy.authentication.applicant.repository.login.IApplicantLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApplicantLoginController {

    private final IApplicantLoginRepository applicantLoginRepository;

    @Autowired
    public ApplicantLoginController(IApplicantLoginRepository applicantLoginRepository) {
        this.applicantLoginRepository = applicantLoginRepository;
    }

   @GetMapping("/applicant/login")
    public String showApplicantLoginForm() {
        return "login_applicant";
    }
    @GetMapping("login")
    public String login (Model model) {
        Applicant applicant = new Applicant();
        model.addAttribute("applicant",applicant);
        return "login_applicant";
    }

    @PostMapping("/auth/applicant/login")
    public Applicant loginApplicant(@RequestBody Login login) throws ApplicantAuthenticationException {
        if (login == null) {
            throw new ApplicantAuthenticationException("Login details are not found...");
        } else {
            login.setEmailId(login.getEmailId());
            login.setPassword(login.getPassword());
        }
        return this.applicantLoginRepository.checkLoginDetails(login);
    }
}
