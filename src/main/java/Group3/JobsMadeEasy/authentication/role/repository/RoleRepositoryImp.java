package Group3.JobsMadeEasy.authentication.role.repository;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.model.RoleMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
        return getJdbcTemplate().query(sql,new RoleMapper(),id)
                .stream()
                .findFirst();
    }

}
