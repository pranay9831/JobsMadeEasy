package Group3.JobsMadeEasy.authentication.user.dao.login;

import Group3.JobsMadeEasy.authentication.role.dao.RoleDaoConstant;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.authentication.role.dao.RoleDaoConstant.ROLE_ID_COLUMN;
import static Group3.JobsMadeEasy.authentication.user.dao.login.UserLoginConstant.*;

@Component
public class UserLoginDaoImp implements IUserLoginDao{

    private static UserLoginDaoImp obj;

    private UserLoginDaoImp() {
    }

    public static UserLoginDaoImp getInstance() {
        if (obj == null) {
            obj = new UserLoginDaoImp();
        }
        return obj;
    }

    @Override
    public String checkLoginDetails(Login login) {
        return "SELECT * FROM "+ USER_TABLE  + " INNER JOIN " + ROLE_TABLE + " ON " + USER_TABLE + "." +
                ROLE_ID_COLUMN + " = " + ROLE_TABLE + "." + ROLE_ID_COLUMN +
                " WHERE "+ USER_EMAIL_ID_COLUMN + " = " + "\""+ login.getEmailId()
                +"\"" + " AND " + USER_PASSWORD_COLUMN + " = " + "\"" + login.getPassword()
                +"\"";
    }
}
