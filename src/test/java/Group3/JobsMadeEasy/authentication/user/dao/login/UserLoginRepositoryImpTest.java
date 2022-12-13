package Group3.JobsMadeEasy.authentication.user.repository.login;

import Group3.JobsMadeEasy.authentication.user.dao.login.UserLoginDaoImp;
import Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationDaoImp;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.model.User;
import org.junit.Before;
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
import org.thymeleaf.spring5.expression.Fields;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserLoginRepositoryImpTest {

    @Mock
    UserLoginDaoImp userLoginDaoImp;

    @Test
    public void checkLoginDetailsTest() {
        Login login = new Login("deep@dal.ca","Deep@123");
    }

}
