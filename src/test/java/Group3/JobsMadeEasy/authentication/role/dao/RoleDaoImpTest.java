package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RoleDaoImpTest {

    @Test
    public void createRoleTest() {
        Role role = new Role(1,"admin");
        RoleDaoImp roleDaoImp = RoleDaoImp.getInstance();
        String queryGenerated = roleDaoImp.createRole(role);
        String queryExpected = "INSERT INTO role (roleId,roleName)"+
                "VALUES (\"1\",\"admin\");";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void getRoleTest() {
        int roleId = 1;
        RoleDaoImp roleDaoImp = RoleDaoImp.getInstance();
        String queryGenerated = roleDaoImp.getRole(roleId);
        String queryExpected = "SELECT * FROM role WHERE roleId = " + roleId + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void viewAllRolesTest() {
        RoleDaoImp roleDaoImp = RoleDaoImp.getInstance();
        String queryGenerated = roleDaoImp.viewAllRoles();
        String queryExpected = "SELECT * FROM role;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void deleteRoleByIdTest(){
        int roleId = 1;
        RoleDaoImp roleDaoImp = RoleDaoImp.getInstance();
        String queryGenerated = roleDaoImp.deleteRoleById(roleId);
        String queryExpected = "DELETE FROM role WHERE roleId = " + roleId + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query match");
    }
}
