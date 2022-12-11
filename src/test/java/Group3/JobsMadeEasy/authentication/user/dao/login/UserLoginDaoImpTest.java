package Group3.JobsMadeEasy.authentication.user.dao.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserLoginDaoImpTest {

    @Test
    public void checkLoginDetailsTest() {
        String email = "A@gmail.com";
        String password = "1234";
        Login login = new Login(email,password);
        UserLoginDaoImp userLoginDaoImp = UserLoginDaoImp.getInstance();
        String queryGenerated = userLoginDaoImp.checkLoginDetails(login);
        String queryExpected = "SELECT * FROM user u INNER JOIN role r ON u.roleId = r.roleId WHERE emailId = " +email+
                " AND password = " + password + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query match");
    }
}
