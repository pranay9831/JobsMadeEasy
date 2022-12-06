package Group3.JobsMadeEasy.authentication.role.repository;


import Group3.JobsMadeEasy.authentication.role.model.Role;

import java.util.Optional;

public interface IRoleRepository {

    Role createRole(Role role);
    Optional<Role> getRole(int id);

}
