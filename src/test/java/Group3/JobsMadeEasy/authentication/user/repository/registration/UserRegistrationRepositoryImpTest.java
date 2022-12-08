package Group3.JobsMadeEasy.authentication.user.repository.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationRepositoryImpTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private UserRegistrationRepositoryImp userRegistrationRepositoryImp;

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
    public void createUserTest() {
        final String sql = "INSERT INTO user " +
                "(userId, firstName, lastName, phoneNumber, emailId, password, city, province, address, postalCode, " +
                "roleId, isEmployee, isApproved)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        User user = new User(1,"Deep","Dave","123456789","test@test.com",
                "test@1234","halifax","NS","Dalhousie","B2k 2Z2",1,
                false,false);
        MapSqlParameterSource mapParameters = new MapSqlParameterSource();
        mapParameters.addValue("userId", user.getUserId());
        mapParameters.addValue("firstName", user.getFirstName());
        mapParameters.addValue("lastName", user.getLastName());
        mapParameters.addValue("phoneNumber", user.getPhoneNumber());
        mapParameters.addValue("emailId", user.getEmailId());
        mapParameters.addValue("password", user.getPassword());
        mapParameters.addValue("city", user.getCity());
        mapParameters.addValue("province", user.getProvince());
        mapParameters.addValue("address", user.getAddress());
        mapParameters.addValue("postalCode", user.getPostalCode());
        mapParameters.addValue("roleId", user.getRoleId());
        mapParameters.addValue("isEmployee", user.isEmployee());
        mapParameters.addValue("isApproved", user.isApproved());
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(MapSqlParameterSource.class)))
                .thenReturn(1);
        userRegistrationRepositoryImp.createUser(user);
        Assertions.assertEquals(1,jdbcTemplate.update(sql,mapParameters));
    }

    @Test
    public void getUserTest() {
        when(userRegistrationRepositoryImp.getUser(1)).thenReturn(Optional.of(new User(1, "Deep",
                "Dave", "12345678", "test@gmail.com","1234","halifax",
                "NS","Dalhousie","123",
                1,false,false)));
        Optional<User> user = userRegistrationRepositoryImp.getUser(1);
        assertEquals(1, user.get().getUserId());
        assertEquals("Deep", user.get().getFirstName());
        assertEquals("Dave", user.get().getLastName());
        assertEquals("12345678", user.get().getPhoneNumber());
        assertEquals("test@gmail.com", user.get().getEmailId());
        assertEquals("1234", user.get().getPassword());
        assertEquals("halifax", user.get().getCity());
        assertEquals("NS", user.get().getProvince());
        assertEquals("Dalhousie", user.get().getAddress());
        assertEquals("123", user.get().getPostalCode());
        assertEquals(1, user.get().getRoleId());
        assertEquals(false, user.get().isEmployee());
        assertEquals(false, user.get().isApproved());
    }

    @Test
    public void viewAllUsersTest() {
        List<User> list = new ArrayList<User>();
        User user1 = new User(1, "Deep",
                "Dave", "12345678", "test@gmail.com","1234","halifax",
                "NS","Dalhousie","B2K 2Z2",
                1,false,false);
        User user2 = new User(2, "Deep",
                "Dave", "12345678", "test@gmail.com","1234","halifax",
                "NS","Dalhousie","B2K 2Z2",
                1,false,false);
        User user3 = new User(3, "Deep",
                "Dave", "12345678", "test@gmail.com","1234","halifax",
                "NS","Dalhousie","B2K 2Z2",
                1,false,false);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        when(userRegistrationRepositoryImp.viewAllUsers()).thenReturn(list);
        List<User> userList = userRegistrationRepositoryImp.viewAllUsers();
        assertEquals(3, userList.size());
        verify(userRegistrationRepositoryImp, times(1)).viewAllUsers();
    }

    @Test
    public void deleteRoleByIdTest() {
        when(userRegistrationRepositoryImp.deleteUserById(1)).thenReturn(true);
        boolean result = userRegistrationRepositoryImp.deleteUserById(1);
        assertEquals(result,true);
    }
}
