package Group3.JobsMadeEasy.authentication.user.dao.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;

public interface IUserLoginDao {
    String checkLoginDetails(Login login);
}
