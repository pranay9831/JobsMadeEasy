package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import java.util.*;

public class RoleMockDB{

    private final List<Role> mockDB;

    public RoleMockDB(){
        mockDB = new ArrayList<>();
        mockDB.add(new Role(1,"admin"));
        mockDB.add(new Role(2,"hr"));
    }

    public boolean createRole(Role role) {
        Optional<Role> roleDB = getRole(role.getRoleId());
        if(roleDB == null){
            return false;
        }
        return true;
    }

    public Optional<Role> getRole(int id) {
        ListIterator<Role> iterator = mockDB.listIterator();
        while (iterator.hasNext()){
            Role currentRole = iterator.next();
            if(currentRole.getRoleId() == id){
                return Optional.of(currentRole);
            }
        }
        return null;
    }

    public List<Role> viewAllRoles(){
        return mockDB;
    }

    public boolean deleteRoleById(int id) {
        Optional<Role> role = getRole(id);
        if(role == null){
            return false;
        }
        return true;
    }
}
