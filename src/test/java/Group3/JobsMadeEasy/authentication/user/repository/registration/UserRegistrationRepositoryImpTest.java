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
}
