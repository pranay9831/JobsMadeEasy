package Group3.JobsMadeEasy.authentication.applicant.controller.registration;

import Group3.JobsMadeEasy.authentication.applicant.exception.ApplicantAuthenticationException;
import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import Group3.JobsMadeEasy.authentication.applicant.repository.registration.IApplicantRegistrationRepository;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicantRegistrationController {

    private final IApplicantRegistrationRepository applicantRegistrationRepository;
    private static GenerateIdUtil obj = new GenerateIdUtil();
    @Autowired
    public ApplicantRegistrationController(IApplicantRegistrationRepository applicantRegistrationRepository) {
        this.applicantRegistrationRepository = applicantRegistrationRepository;
    }
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("register")
    public String register (Model model) {
        Applicant applicant = new Applicant();
        model.addAttribute("applicant",applicant);
        return "register_applicant";
    }

    @PostMapping(value ="/afterRegister",headers = "Accept=application/json")
    public boolean createApplicant(@ModelAttribute("applicant") Applicant applicant) throws ApplicantAuthenticationException {
        System.out.println(applicant.toString() + "applicant here");
        if (applicant == null) {
            throw new ApplicantAuthenticationException("Applicant register details not found..");
        } else {
            applicant.setApplicantId(obj.generateRandomId());
            applicant.setFirstName(applicant.getFirstName());
            applicant.setLastName(applicant.getLastName());
            applicant.setPhoneNumber(applicant.getPhoneNumber());
            applicant.setEmailId(applicant.getEmailId());
            applicant.setPassword(applicant.getPassword());
            applicant.setCity(applicant.getCity());
            applicant.setProvince(applicant.getProvince());
            applicant.setAddress(applicant.getAddress());
            applicant.setPostalCode(applicant.getPostalCode());
            applicant.setApplicantStatus(true);
        }
        this.applicantRegistrationRepository.createApplicant(applicant);
        System.out.printf("applicant created......");
        return true;
    }
}
