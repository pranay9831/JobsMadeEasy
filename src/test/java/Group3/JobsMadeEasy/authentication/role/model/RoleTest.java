package Group3.JobsMadeEasy.authentication.role.model;

import Group3.JobsMadeEasy.authentication.role.dao.RoleMockDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    private static RoleMockDB roleMockDB;

    @Test
    public void testRoleClass() {
        Role role = Mockito.spy(new Role());
        role.getRoleId();
        role.getRoleName();
        role.setRoleId(1);
        role.setRoleName("ADMIN");
        assertEquals(role.getRoleId(), 1);
        assertEquals(role.getRoleName(), "ADMIN");
    }

    @BeforeAll
    public static void init(){
        roleMockDB = new RoleMockDB();
    }

    @Test
    public void createRoleTest(){
        boolean result = roleMockDB.createRole(new Role(1,"admin"));
        assertEquals(result,true);
    }

    @Test
    public void getRoleTest(){
        int userId = 1;
        Optional<Role> role = roleMockDB.getRole(userId);
        assertEquals(1,role.get().getRoleId());
        assertEquals("admin",role.get().getRoleName());
    }

    @Test
    public void getAllRoles(){
        List<Role> roles = roleMockDB.viewAllRoles();
        assertEquals(2,roles.size());
    }

    @Test
    public void deleteRoleByIdTest(){
        int userId = 1;
        boolean role = roleMockDB.deleteRoleById(userId);
        assertEquals(true,role);
    }
}
