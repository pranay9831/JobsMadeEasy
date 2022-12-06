package Group3.JobsMadeEasy.authentication.role.controller;


import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.repository.IRoleRepository;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final IRoleRepository roleRepository;

    public RoleController(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping("/add_role")
    public boolean createUser(@RequestBody Role role) throws JobsMadeEasyException {
        if (role == null) {
            throw new JobsMadeEasyException("user register details not found..");
        } else {
            role.setRoleId(role.getRoleId());
            role.setRoleName(role.getRoleName());
        }
        this.roleRepository.createRole(role);
        return true;
    }

    @GetMapping("/get_role_by_id")
    public boolean getRoleById(@RequestBody int id) throws JobsMadeEasyException {
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        } else {
            this.roleRepository.getRole(id);
        }
        return true;
    }

}
