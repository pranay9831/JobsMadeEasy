/*
package Group3.JobsMadeEasy.authentication.user.repository.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;
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
public class UserLoginRepositoryImpTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private UserLoginRepositoryImp userLoginRepositoryImp;

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
    public void checkLoginDetailsTest() {
        final String sql = "SELECT * FROM user u INNER JOIN role r ON u.roleId = r.roleId WHERE emailId = ? AND  password = ?";
        Login login = new Login("test@gmail.com","test@123");
        MapSqlParameterSource mapParameters = new MapSqlParameterSource();
        mapParameters.addValue("emailId", login.getEmailId());
        mapParameters.addValue("password", login.getPassword());
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(MapSqlParameterSource.class)))
                .thenReturn(1);
        userLoginRepositoryImp.checkLoginDetails(login);
        Assertions.assertEquals(1,jdbcTemplate.update(sql,mapParameters));
    }

}
*/
