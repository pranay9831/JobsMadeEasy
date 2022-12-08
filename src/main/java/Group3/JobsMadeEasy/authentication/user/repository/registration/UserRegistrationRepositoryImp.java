package Group3.JobsMadeEasy.authentication.user.repository.registration;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.model.RoleMapper;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


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

    @Override
    public Optional<User> getUser(int id) {
        String sql = "SELECT * FROM user WHERE userId = ?";
        System.out.println(id + "id>>>>>>>>>>");
        return getJdbcTemplate().query(sql, new UserMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<User> viewAllUsers() {
        String sql = "SELECT * FROM user";
        return getJdbcTemplate().query(
                sql,
                (rs, rowNum) ->
                        new User(
                                rs.getInt("userId"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("phoneNumber"),
                                rs.getString("emailId"),
                                rs.getString("password"),
                                rs.getString("city"),
                                rs.getString("province"),
                                rs.getString("address"),
                                rs.getString("postalCode"),
                                rs.getInt("roleId"),
                                rs.getBoolean("isEmployee"),
                                rs.getBoolean("isApproved")
                        ));
    }

    @Override
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM user WHERE userId= ?";
        Object[] args = new Object[] {id};
        return getJdbcTemplate().update(sql, args) == 1;
    }
}
