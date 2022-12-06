package Group3.JobsMadeEasy.authentication.user.controller.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.repository.registration.IUserRegistrationRepository;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    private final IUserRegistrationRepository userRegistrationRepository;

    public UserRegistrationController(IUserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
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
    public boolean createUser(@RequestBody User user) throws JobsMadeEasyException {
        if (user == null) {
            throw new JobsMadeEasyException("user register details not found..");
        } else {
            System.out.println(user.toString() + "user>>>");
            user.setUserId(GenerateIdUtil.Object().generateRandomId());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setPhoneNumber(user.getPhoneNumber());
            user.setEmailId(user.getEmailId());
            user.setPassword(user.getPassword());
            user.setCity(user.getCity());
            user.setProvince(user.getProvince());
            user.setAddress(user.getAddress());
            user.setPostalCode(user.getPostalCode());
            user.setRoleId(1);
            user.setEmployee(false);
            user.setApproved(false);
        }
        this.userRegistrationRepository.createUser(user);
        return true;
    }
}
