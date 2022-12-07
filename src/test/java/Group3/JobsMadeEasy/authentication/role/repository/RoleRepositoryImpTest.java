package Group3.JobsMadeEasy.authentication.role.repository;

import Group3.JobsMadeEasy.authentication.user.repository.registration.UserRegistrationRepositoryImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class RoleRepositoryImpTest {
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


}
