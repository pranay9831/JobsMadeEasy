package Group3.JobsMadeEasy.authentication.user.dao.registration;

import Group3.JobsMadeEasy.authentication.role.dao.RoleDaoImp;
import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationConstant.USER_ID_COLUMN;
import static Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationConstant.USER_TABLE;

public class UserRegistrationDaoImpTest {

    @Test
    public void createUserTest() {
        User user = new User(1,"Deep","Dave","789678456", "deep@dal.ca", "test@123", "halifax", "NS", "Dalhousie",
                "B2k 2Z2",1,false,false);
        UserRegistrationDaoImp userRegistrationDaoImp = UserRegistrationDaoImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.createUser(user);
        String queryExpected = "INSERT INTO user (userId,firstName,lastName,phoneNumber,emailId,password,city,province,address,postalCode,roleId,isEmployee,isApproved) VALUES " +
                "(\"1\",\"Deep\",\"Dave\",\"789678456\",\"deep@dal.ca\",\"test@123\",\"halifax\",\"NS\",\"Dalhousie\",\"B2k 2Z2\",\"1\",false,false);";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void getUserByIdTest() {
        int userId = 1;
        UserRegistrationDaoImp userRegistrationDaoImp = UserRegistrationDaoImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.getUserById(userId);
        String queryExpected = "SELECT * FROM user WHERE userId = 1;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void viewAllUsersTest() {
        UserRegistrationDaoImp userRegistrationDaoImp = UserRegistrationDaoImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.getUsers();
        String queryExpected = "SELECT * FROM user;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void deleteRoleByIdTest(){
        int userId = 1;
        UserRegistrationDaoImp userRegistrationDaoImp = UserRegistrationDaoImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.deleteUserById(userId);
        String queryExpected = "DELETE FROM user WHERE userId = 1;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }
}
