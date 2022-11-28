package Group3.JobsMadeEasy.authentication.employee.repository.registration;

import Group3.JobsMadeEasy.authentication.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Service
public class EmployeeRegistrationRepositoryImp extends JdbcDaoSupport implements IEmployeeRegistrationRepository{

    DataSource dataSource;

    @Autowired
    public EmployeeRegistrationRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public boolean createEmployee(Employee employee) {
        String sql = "INSERT INTO employee " +
                "(employeeId, firstName, lastName, phoneNumber, emailId, password, role, joinDate, city, province, " +
                "address, postalCode," +
                "employeeStatus)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                employee.getEmployeeId(), employee.getFirstName(), employee.getLastName()
                , employee.getPhoneNumber(), employee.getEmailId(), employee.getPassword(), employee.getRole(),
                employee.getJoinDate(), employee.getCity(), employee.getProvince(), employee.getAddress()
                , employee.getPostalCode(), employee.isEmployeeStatus()
        });
        return true;
    }
}
