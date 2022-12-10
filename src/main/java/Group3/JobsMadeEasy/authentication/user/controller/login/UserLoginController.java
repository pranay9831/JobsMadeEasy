package Group3.JobsMadeEasy.authentication.user.controller.login;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.repository.IRoleRepository;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.repository.login.IUserLoginRepository;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserLoginController {

    private final IUserLoginRepository userLoginRepository;
    private final IRoleRepository roleRepository;
    private HttpSession session;
    public UserLoginController(IUserLoginRepository userLoginRepository, IRoleRepository roleRepository,
                               HttpSession session) {
        this.userLoginRepository = userLoginRepository;
        this.roleRepository = roleRepository;
        this.session = session;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Login login = new Login();
        model.addAttribute("login",login);
        return "login";
    }

    @PostMapping("/auth/login")
    public String loginApplicant(@ModelAttribute Login login) throws JobsMadeEasyException {
        login.setEmailId(login.getEmailId());
        login.setPassword(login.getPassword());
        User user = this.userLoginRepository.checkLoginDetails(login);
        Optional<Role> role = this.roleRepository.getRole(user.getRoleId());
        session.setAttribute("role", role.get().getRoleName());
        session.setAttribute("currentId", user.getUserId());
        session.setAttribute("currentName", user.getFirstName());
        System.out.println(session.getAttribute("role") + ">>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(session.getAttribute("currentId") + ">>>>>>>>>>>>>>>>>>>>>>");
        return checkCurrentUserRole(role.get().getRoleName());
    }

    public String checkCurrentUserRole(String roleName) {
        return roleName + "HomePage";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("role");
        session.removeAttribute("currentId");
        session.removeAttribute("currentName");
        return "index";
    }

}
