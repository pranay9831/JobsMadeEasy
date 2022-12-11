package Group3.JobsMadeEasy.authentication.user.controller.login;

import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.authentication.user.dao.login.IUserLoginDao;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class UserLoginController {

    private final IUserLoginDao userLoginDao;
    private final IRoleDao roleDao;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    private HttpSession session;
    public UserLoginController(
            IUserLoginDao userLoginDao, IRoleDao roleDao, DatabaseSetup databaseSetup, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        this.userLoginDao = userLoginDao;
        this.roleDao = roleDao;
        this.databaseSetup = databaseSetup;
        this.session = session;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Login login = new Login();
        model.addAttribute("login",login);
        return "login";
    }

    @PostMapping("/auth/login")
    public String loginApplicant(@ModelAttribute Login login) throws JobsMadeEasyException, SQLException {
        ResultSet rs = null, resultSet = null;
        login.setEmailId(login.getEmailId());
        login.setPassword(login.getPassword());
        try {
            String checkLoginDetailsQuery = userLoginDao.checkLoginDetails(login);
            System.out.println("ooooooo" + checkLoginDetailsQuery);
            rs = statement.executeQuery(checkLoginDetailsQuery);
            if(rs.next()){
                System.out.println("ooooooo" + rs.getString("userId"));
                session.setAttribute("currentId", rs.getString("userId"));
                session.setAttribute("currentName", rs.getString("firstName"));
                String getRoleByIdQuery = roleDao.getRole(rs.getInt("roleId"));
                resultSet = statement.executeQuery(getRoleByIdQuery);
                if(resultSet.next()){
                    session.setAttribute("role",resultSet.getString("roleName"));
                }
                return checkCurrentUserRole((String) session.getAttribute("role"));
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
            resultSet.close();
        }
        return null;
    }

    public String checkCurrentUserRole(String roleName) {
        return roleName + "HomePage";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("role");
        session.removeAttribute("currentId");
        session.removeAttribute("currentName");
        return "index";
    }

}
