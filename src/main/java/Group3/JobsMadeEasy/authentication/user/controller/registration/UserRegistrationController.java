package Group3.JobsMadeEasy.authentication.user.controller.registration;

import Group3.JobsMadeEasy.authentication.user.dao.login.IUserLoginDao;
import Group3.JobsMadeEasy.authentication.user.dao.registration.IUserRegistrationDao;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserRegistrationController {

    private final User user;

    public UserRegistrationController(IUserLoginDao userLoginDao, IUserRegistrationDao userRegistrationDao){
        this.user = new User(userLoginDao,userRegistrationDao);
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/auth/register")
    public String createUser(@ModelAttribute User user) throws JobsMadeEasyException {
        return this.user.createUser(user);
    }

    @GetMapping("/get_user_by_id/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return user.getUserById(id);
    }

    @GetMapping("/get_all_users")
    public List<User> getAllUsers() throws JobsMadeEasyException, SQLException {
        return user.getAllUsers();
    }

    @DeleteMapping("/delete_user_by_id/{id}")
    public boolean deleteUserById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return user.deleteUserById(id);
    }

    @GetMapping("/view_all_users")
    public String viewAllUsers(Model model) {
        List<User> users;
        try {
            users = getAllUsers();
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("users", users);
        return "viewAllUsers";
    }

    @GetMapping("/view_user_by_id/{id}")
    public String viewUserById(Model model, @PathVariable int id) throws SQLException, JobsMadeEasyException {

        Optional<User> users = getUserById(id);
        model.addAttribute("users", users);
        return "viewUserById";
    }

    @GetMapping("/delete_user_by_id")
    public String deleteUserByUserId(Model model) throws JobsMadeEasyException, SQLException {
        List<User> user = getAllUsers();
        model.addAttribute("user", user);
        return "deleteUserById";
    }
}
