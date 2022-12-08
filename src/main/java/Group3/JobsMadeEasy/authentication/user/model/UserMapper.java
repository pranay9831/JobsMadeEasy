package Group3.JobsMadeEasy.authentication.user.model;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
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
        );
    }
}
