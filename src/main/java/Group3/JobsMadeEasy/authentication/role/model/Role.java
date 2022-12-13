package Group3.JobsMadeEasy.authentication.role.model;

import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class Role {
    private int roleId;
    private String roleName;
    private IRoleDao roleDao;
    public Role(){}
    public Role(IRoleDao roleDao){
        this.roleDao = roleDao;
    }
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean createRole(Role role) throws JobsMadeEasyException {
        if (role == null) {
            throw new JobsMadeEasyException("role details not found..");
        }
        return roleDao.createRole(role);
    }

    public Optional<Role> getRoleById(int id) throws SQLException, JobsMadeEasyException {
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        }
        return roleDao.getRole(id);
    }

    public List<Role> getAllRoles() throws SQLException, JobsMadeEasyException {
        return roleDao.viewAllRoles();
    }

    public boolean deleteRoleById(int id) throws SQLException, JobsMadeEasyException {
        if (this.getRoleById(id).isPresent())
        {
            return roleDao.deleteRoleById(id);
        }else
        {
            throw new JobsMadeEasyException("No role found in DB");
        }
    }
}
