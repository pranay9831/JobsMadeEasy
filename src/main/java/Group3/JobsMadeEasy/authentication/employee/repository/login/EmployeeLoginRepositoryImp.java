package Group3.JobsMadeEasy.authentication.employee.repository.login;

import Group3.JobsMadeEasy.authentication.employee.model.Employee;
import Group3.JobsMadeEasy.authentication.employee.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Service
public class EmployeeLoginRepositoryImp extends JdbcDaoSupport implements IEmployeeLoginRepository {

    DataSource dataSource;

    @Autowired
    public EmployeeLoginRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Employee checkLoginDetailsEmployee(Login login) {
        String sql = "SELECT * FROM employee WHERE emailId = ? AND  password = ?";
        return getJdbcTemplate().queryForObject(sql, (rs, rwNumber) -> {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("employeeId"));
            employee.setFirstName(rs.getString("firstName"));
            employee.setLastName(rs.getString("lastName"));
            employee.setPhoneNumber(rs.getString("phoneNumber"));
            employee.setEmailId(rs.getString("emailId"));
            employee.setPassword(rs.getString("password"));
            employee.setRole(rs.getString("role"));
            employee.setJoinDate(rs.getString("joinDate"));
            employee.setCity(rs.getString("city"));
            employee.setProvince(rs.getString("province"));
            employee.setAddress(rs.getString("address"));
            employee.setPostalCode(rs.getString("postalCode"));
            employee.setEmployeeStatus(rs.getBoolean("employeeStatus"));
            return employee;
        }, new Object[]{login.getEmailId(), login.getPassword()});
    }
}
