package Group3.JobsMadeEasy.authentication.user.repository.login;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

public interface IUserLoginRepository {

    User checkLoginDetails(Login login) throws JobsMadeEasyException;
}
