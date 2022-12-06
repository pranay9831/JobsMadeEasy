package Group3.JobsMadeEasy.authentication.user.repository.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Repository
public class UserRegistrationRepositoryImp extends JdbcDaoSupport implements IUserRegistrationRepository {

    DataSource dataSource;

    public UserRegistrationRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO user " +
                "(userId, firstName, lastName, phoneNumber, emailId, password, city, province, address, postalCode, " +
                "roleId, isEmployee, isApproved)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                user.getUserId(), user.getFirstName(), user.getLastName()
                , user.getPhoneNumber(), user.getEmailId(), user.getPassword()
                , user.getCity(), user.getProvince(), user.getAddress()
                , user.getPostalCode(), user.getRoleId(), user.isEmployee(), user.isApproved()
        });
        return true;
    }
}
