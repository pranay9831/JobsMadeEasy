package Group3.JobsMadeEasy.authentication.user.repository.registration;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.user.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRegistrationRepository {

    boolean createUser(User user);
    Optional<User> getUser(int id);
    List<User> viewAllUsers();
    boolean deleteUserById(int id);

}
