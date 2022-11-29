package Group3.JobsMadeEasy.authentication.employee.controller.login;


import Group3.JobsMadeEasy.authentication.employee.exception.EmployeeAuthenticationException;
import Group3.JobsMadeEasy.authentication.employee.model.Employee;
import Group3.JobsMadeEasy.authentication.employee.model.Login;
import Group3.JobsMadeEasy.authentication.employee.repository.login.IEmployeeLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeLoginController {

    private final IEmployeeLoginRepository employeeLoginRepository;

    @Autowired
    public EmployeeLoginController(IEmployeeLoginRepository employeeLoginRepository) {
        this.employeeLoginRepository = employeeLoginRepository;
    }

    @GetMapping("/employee/login")
    public String showEmployeeLoginForm() {
        return "login_employee";
    }

    @PostMapping("/auth/employee/login")
    public Employee loginEmployee(@RequestBody Login login) throws EmployeeAuthenticationException {
        if (login == null) {
            throw new EmployeeAuthenticationException("Login details are not found for the employee...");
        } else {
            login.setEmailId(login.getEmailId());
            login.setPassword(login.getPassword());
        }
        return this.employeeLoginRepository.checkLoginDetailsEmployee(login);
    }
}
