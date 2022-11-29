package Group3.JobsMadeEasy.authentication.employee.repository.login;


import Group3.JobsMadeEasy.authentication.employee.model.Employee;
import Group3.JobsMadeEasy.authentication.employee.model.Login;

public interface IEmployeeLoginRepository {

    Employee checkLoginDetailsEmployee(Login login);

}
