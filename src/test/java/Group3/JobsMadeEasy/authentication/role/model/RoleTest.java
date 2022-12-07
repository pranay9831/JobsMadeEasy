package Group3.JobsMadeEasy.authentication.role.model;

import Group3.JobsMadeEasy.authentication.user.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {
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
}
