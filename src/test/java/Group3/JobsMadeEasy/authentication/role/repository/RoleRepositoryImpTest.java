package Group3.JobsMadeEasy.authentication.role.repository;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleRepositoryImpTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private RoleRepositoryImp roleRepositoryImp;

    @BeforeEach
    public void setup() {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void createRoleTest() {
        final String sql = "INSERT INTO role " +
                "(roleId, roleName)" +
                " VALUES (?, ?)";
        Role role = new Role(1,"ADMIN");
        MapSqlParameterSource mapParameters = new MapSqlParameterSource();
        mapParameters.addValue("roleId", role.getRoleId());
        mapParameters.addValue("roleName", role.getRoleName());
        when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(MapSqlParameterSource.class)))
                .thenReturn(1);
        roleRepositoryImp.createRole(role);
        assertEquals(1,jdbcTemplate.update(sql,mapParameters));
    }

    @Test
    public void getRoleTest() {
        when(roleRepositoryImp.getRole(1)).thenReturn(Optional.of(new Role(1, "ADMIN")));
        Optional<Role> role = roleRepositoryImp.getRole(1);
        assertEquals(1, role.get().getRoleId());
        assertEquals("ADMIN", role.get().getRoleName());
    }

    @Test
    public void viewAllRolesTest() {
        List<Role> list = new ArrayList<Role>();
        Role role1 = new Role(1, "ADMIN");
        Role role2 = new Role(2,"HR");
        Role role3 = new Role(3, "APPLICANT");
        list.add(role1);
        list.add(role2);
        list.add(role3);
        when(roleRepositoryImp.viewAllRoles()).thenReturn(list);
        List<Role> roleList = roleRepositoryImp.viewAllRoles();
        assertEquals(3, roleList.size());
        verify(roleRepositoryImp, times(1)).viewAllRoles();
    }

    @Test
    public void deleteRoleByIdTest() {
        when(roleRepositoryImp.deleteRoleById(1)).thenReturn(true);
        boolean result = roleRepositoryImp.deleteRoleById(1);
        assertEquals(result,true);
    }


}
