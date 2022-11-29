package Group3.JobsMadeEasy.authentication.applicant.controller.registration;

import Group3.JobsMadeEasy.authentication.applicant.exception.ApplicantAuthenticationException;
import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import Group3.JobsMadeEasy.authentication.applicant.repository.registration.IApplicantRegistrationRepository;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("/applicant/register")
    public String showApplicantRegistrationForm(Model model) {
        Applicant applicant = new Applicant();
        model.addAttribute("applicant", applicant);
        return "register_applicant";
    }

    @PostMapping("/auth/applicant/register")
    public boolean createApplicant(@RequestBody Applicant applicant) throws ApplicantAuthenticationException {
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
        return true;
    }
}
