package Group3.JobsMadeEasy.authentication.role.repository;


import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.job_post.model.JobPost;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository {

    Role createRole(Role role);
    Optional<Role> getRole(int id);
    List<Role> viewAllRoles();
    boolean deleteRoleById(int id);

}
