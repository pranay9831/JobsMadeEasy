package Group3.JobsMadeEasy.authentication.applicant.controller.registration;

import Group3.JobsMadeEasy.authentication.applicant.exception.ApplicantAuthenticationException;
import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import Group3.JobsMadeEasy.authentication.applicant.repository.registration.IApplicantRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicantRegistrationController{

    private final IApplicantRegistrationRepository applicantRegistrationRepository;

    @Autowired
    public ApplicantRegistrationController(IApplicantRegistrationRepository applicantRegistrationRepository) {
        this.applicantRegistrationRepository = applicantRegistrationRepository;
    }


    @PostMapping("/auth/applicant/register")
    public boolean createApplicant(@RequestBody Applicant applicant) throws ApplicantAuthenticationException {
        if(applicant == null){
            throw new ApplicantAuthenticationException("Applicant register details not found..");
        }else{
            applicant.setApplicantId(applicant.getApplicantId());
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
