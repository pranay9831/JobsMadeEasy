package Group3.JobsMadeEasy.authentication.user.controller.login;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.repository.IRoleRepository;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.repository.login.IUserLoginRepository;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class UserLoginController {

    private final IUserLoginRepository userLoginRepository;
    private final IRoleRepository roleRepository;

    public UserLoginController(IUserLoginRepository userLoginRepository, IRoleRepository roleRepository) {
        this.userLoginRepository = userLoginRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/auth/login")
    public User loginApplicant(@RequestBody Login login, HttpSession session) throws JobsMadeEasyException {
        login.setEmailId(login.getEmailId());
        login.setPassword(login.getPassword());
        User user = this.userLoginRepository.checkLoginDetails(login);
        Optional<Role> role = this.roleRepository.getRole(user.getRoleId());
        session.setAttribute("role", role.get().getRoleName());
        System.out.println(session.getAttribute("role") + ">>>>>>>>>>>>>>>>>>>>>>");
        return user;
    }
}
