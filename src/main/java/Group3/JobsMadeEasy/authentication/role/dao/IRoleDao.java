package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;

public interface IRoleDao {

    String createRole(Role role);
    String getRole(int id);
    String viewAllRoles();
    String deleteRoleById(int id);
}
