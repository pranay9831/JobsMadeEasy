package Group3.JobsMadeEasy.authentication.user.controller.login;

import Group3.JobsMadeEasy.authentication.user.dao.login.IUserLoginDao;
import Group3.JobsMadeEasy.authentication.user.dao.registration.IUserRegistrationDao;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@Controller
public class UserLoginController {

    private final User user;

    public UserLoginController(IUserLoginDao userLoginDao, IUserRegistrationDao userRegistrationDao) {
        this.user = new User(userLoginDao,userRegistrationDao);
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Login login = new Login();
        model.addAttribute("login",login);
        return "login";
    }

    @PostMapping("/auth/login")
    public String loginApplicant(@ModelAttribute Login login) throws SQLException, JobsMadeEasyException {
        return user.checkLoginDetails(login);
    }

    @GetMapping("/logout")
    public String logout(){
        return user.logout();
    }
}
