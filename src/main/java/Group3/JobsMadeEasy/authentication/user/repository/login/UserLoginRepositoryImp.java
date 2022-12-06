package Group3.JobsMadeEasy.authentication.user.repository.login;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class UserLoginRepositoryImp extends JdbcDaoSupport implements IUserLoginRepository {

    DataSource dataSource;

    public UserLoginRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public User checkLoginDetails(Login login) {
        String sql = "SELECT * FROM user u INNER JOIN role r ON u.roleId = r.roleId WHERE emailId = ? AND  password = ?";
            return getJdbcTemplate().queryForObject(sql, (rs, rwNumber) -> {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setEmailId(rs.getString("emailId"));
                user.setPassword(rs.getString("password"));
                user.setCity(rs.getString("city"));
                user.setProvince(rs.getString("province"));
                user.setAddress(rs.getString("address"));
                user.setPostalCode(rs.getString("postalCode"));
                user.setRoleId(rs.getInt("roleId"));
                user.setEmployee(rs.getBoolean("isEmployee"));
                user.setApproved(rs.getBoolean("isApproved"));
                System.out.println(user.toString() + "firstName>>>");
                return user;
            }, new Object[]{login.getEmailId(), login.getPassword()});
    }
}
