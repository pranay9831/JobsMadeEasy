package Group3.JobsMadeEasy.authentication.applicant.repository.login;

import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import Group3.JobsMadeEasy.authentication.applicant.model.Login;

public interface IApplicantLoginRepository {

    Applicant checkLoginDetails(Login login);
}
