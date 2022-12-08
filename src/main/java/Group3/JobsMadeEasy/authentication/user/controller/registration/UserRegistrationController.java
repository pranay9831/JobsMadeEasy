package Group3.JobsMadeEasy.authentication.user.controller.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.repository.registration.IUserRegistrationRepository;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public String createUser(@ModelAttribute User user) throws JobsMadeEasyException {
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
        return "index";
    }

    @GetMapping("/get_user_by_id/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws JobsMadeEasyException {
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        } else {
            return this.userRegistrationRepository.getUser(id);
        }
    }

    @GetMapping("/get_all_users")
    public List<User> getAllUsers()
    {
        List<User> users= new ArrayList<>();
        List<User> newList = this.userRegistrationRepository.viewAllUsers();
        newList.forEach(x -> {users.add(x);});
        return users;
    }

    @DeleteMapping("/delete_user_by_id/{id}")
    public boolean deleteUserById(@PathVariable int id) throws JobsMadeEasyException
    {
        if (this.getUserById(id).isPresent())
        {
            return this.userRegistrationRepository.deleteUserById(id);
        }
        else
        {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }
}
