package Group3.JobsMadeEasy.authentication.user.controller.registration;

import Group3.JobsMadeEasy.authentication.user.dao.registration.IUserRegistrationDao;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.UserMapper;
import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserRegistrationController {

    private final IUserRegistrationDao userRegistrationDao;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    public UserRegistrationController(IUserRegistrationDao userRegistrationDao, DatabaseSetup databaseSetup) throws
            SQLException, IOException, ClassNotFoundException {
        this.userRegistrationDao = userRegistrationDao;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/auth/register")
    public String createUser(@ModelAttribute User user) throws JobsMadeEasyException {
        if (user == null) {
            throw new JobsMadeEasyException("user register details not found..");
        } else {
            System.out.println(user.toString() + "user>>>");
            user.setUserId(GenerateIdUtil.Object().generateRandomId());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setPhoneNumber(user.getPhoneNumber());
            user.setEmailId(user.getEmailId());
            user.setPassword(user.getPassword());
            user.setCity(user.getCity());
            user.setProvince(user.getProvince());
            user.setAddress(user.getAddress());
            user.setPostalCode(user.getPostalCode());
            user.setRoleId(1);
            user.setEmployee(false);
            user.setApproved(false);
        }
        try {
            String createUserQuery = userRegistrationDao.createUser(user);
            int updatedRows = statement.executeUpdate(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            if(updatedRows > 0){
                return "index";
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
        }
        return "register";
    }

    @GetMapping("/get_user_by_id/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        } else {
            try {
                String getUserByIdQuery = userRegistrationDao.getUserById(id);
                rs = statement.executeQuery(getUserByIdQuery);
                if(rs.next()){
                    return Optional.ofNullable(new UserMapper().mapRow(rs, rs.getRow()));
                }
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                rs.close();
            }
        }
        return null;
    }

    @GetMapping("/get_all_users")
    public List<User> getAllUsers() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String getUsersQuery = userRegistrationDao.getUsers();
            rs = statement.executeQuery(getUsersQuery);
            List<User> users = new LinkedList<>();
            while(rs.next()){
                int i = rs.getInt("userId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                String emailId = rs.getString("emailId");
                String password = rs.getString("password");
                String city = rs.getString("city");
                String province = rs.getString("province");
                String address = rs.getString("address");
                String postalCode = rs.getString("postalCode");
                int roleId = rs.getInt("roleId");
                boolean isEmployee = rs.getBoolean("isEmployee");
                boolean isApproved = rs.getBoolean("isApproved");
                User user = new User(i,firstName,lastName,phoneNumber,emailId,password,city,province,address,postalCode,
                        roleId,isEmployee,isApproved);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
    }

    @DeleteMapping("/delete_user_by_id/{id}")
    public boolean deleteUserById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        if (this.getUserById(id).isPresent())
        {
            try {
                String deleteUserByIdQuery = userRegistrationDao.deleteUserById(id);
                statement.executeUpdate(deleteUserByIdQuery);
                return true;
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                statement.close();
            }
        }
        else
        {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }
}
