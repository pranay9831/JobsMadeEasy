package Group3.JobsMadeEasy.authentication.employee.controller.registration;

import Group3.JobsMadeEasy.authentication.employee.exception.EmployeeAuthenticationException;
import Group3.JobsMadeEasy.authentication.employee.model.Employee;
import Group3.JobsMadeEasy.authentication.employee.repository.registration.IEmployeeRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRegistrationController {

    private final IEmployeeRegistrationRepository employeeRegistrationRepository;

    @Autowired
    public EmployeeRegistrationController(IEmployeeRegistrationRepository employeeRegistrationRepository) {
        this.employeeRegistrationRepository = employeeRegistrationRepository;
    }

    @PostMapping("/auth/employee/register")
    public boolean createEmployee(@RequestBody Employee employee) throws EmployeeAuthenticationException {
        if(employee == null){
            throw new EmployeeAuthenticationException("Employee register details not found..");
        }else{
            employee.setEmployeeId(employee.getEmployeeId());
            employee.setFirstName(employee.getFirstName());
            employee.setLastName(employee.getLastName());
            employee.setPhoneNumber(employee.getPhoneNumber());
            employee.setEmailId(employee.getEmailId());
            employee.setPassword(employee.getPassword());
            employee.setRole(employee.getRole());
            employee.setJoinDate(employee.getJoinDate());
            employee.setCity(employee.getCity());
            employee.setProvince(employee.getProvince());
            employee.setAddress(employee.getAddress());
            employee.setPostalCode(employee.getPostalCode());
            employee.setEmployeeStatus(false);
        }
        this.employeeRegistrationRepository.createEmployee(employee);
        return true;
    }
}
