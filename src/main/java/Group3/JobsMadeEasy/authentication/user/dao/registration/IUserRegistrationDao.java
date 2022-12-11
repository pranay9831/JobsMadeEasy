package Group3.JobsMadeEasy.authentication.user.dao.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;

public interface IUserRegistrationDao {
    String createUser(User user);
    String getUserById(int id);
    String getUsers();
    String deleteUserById(int id);
}
