package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.authentication.role.dao.RoleDaoConstant.*;

@Component
public class RoleDaoImp implements IRoleDao{

    private static RoleDaoImp obj;

    private RoleDaoImp() {
    }

    public static RoleDaoImp getInstance() {
        if (obj == null) {
            obj = new RoleDaoImp();
        }
        return obj;
    }
    @Override
    public String createRole(Role role) {
        return "INSERT INTO "+ ROLE_TABLE+
                " (" + ROLE_ID_COLUMN + "," +
                ROLE_NAME_COLUMN+ ")" +
                "VALUES (" +
                "\"" + role.getRoleId() + "\"," +
                "\"" + role.getRoleName() + "\"" +
                ");";
    }

    @Override
    public String getRole(int id) {
        return "SELECT * FROM "+ROLE_TABLE+" WHERE "+ROLE_ID_COLUMN+" = " + id + ";";
    }

    @Override
    public String viewAllRoles() {
        return "SELECT * FROM " + ROLE_TABLE + ";";

    }

    @Override
    public String deleteRoleById(int id) {
        return "DELETE FROM "+ROLE_TABLE+" WHERE "+ROLE_ID_COLUMN+" = " + id + ";";
    }
}
