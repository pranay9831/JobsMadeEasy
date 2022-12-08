package Group3.JobsMadeEasy.authentication.role.controller;


import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.repository.IRoleRepository;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/get_role_by_id/{id}")
    public Optional<Role> getRoleById(@PathVariable int id) throws JobsMadeEasyException {
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        } else {
            return this.roleRepository.getRole(id);
        }
    }

    @GetMapping("/get_all_roles")
    public List<Role> getAllRoles()
    {
        List<Role> roles= new ArrayList<>();
        List<Role> newList = this.roleRepository.viewAllRoles();
        newList.forEach(x -> {roles.add(x);});
        return roles;
    }

    @DeleteMapping("/delete_role/{id}")
    public boolean deleteRoleById(@PathVariable int id) throws JobsMadeEasyException
    {
        if (this.getRoleById(id).isPresent())
        {
            return this.roleRepository.deleteRoleById(id);
        }
        else
        {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }

}
