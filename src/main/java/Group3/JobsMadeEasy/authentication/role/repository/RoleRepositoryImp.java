package Group3.JobsMadeEasy.authentication.role.repository;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.model.RoleMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
public class RoleRepositoryImp extends JdbcDaoSupport implements IRoleRepository {

    DataSource dataSource;

    public RoleRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Role createRole(Role role) {
        String sql = "INSERT INTO role " +
                "(roleId, roleName)" +
                " VALUES (?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                role.getRoleId(), role.getRoleName()
        });
        return role;
    }

    @Override
    public Optional<Role> getRole(int id) {
        String sql = "SELECT * FROM role WHERE roleId = ?";
        return getJdbcTemplate().query(sql, new RoleMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Role> viewAllRoles() {
        String sql = "SELECT * FROM role";
        return getJdbcTemplate().query(
                sql,
                (rs, rowNum) ->
                        new Role(
                                rs.getInt("roleId"),
                                rs.getString("roleName")
                        ));
    }

    @Override
    public boolean deleteRoleById(int id)
    {
        String sql = "DELETE FROM role WHERE roleId= ?";
        Object[] args = new Object[] {id};
        return getJdbcTemplate().update(sql, args) == 1;
    }

}
