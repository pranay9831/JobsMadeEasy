package Group3.JobsMadeEasy.authentication.user.dao.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationConstant.*;

@Component
public class UserRegistrationDaoImp implements IUserRegistrationDao {

    private static UserRegistrationDaoImp obj;

    private UserRegistrationDaoImp() {
    }

    public static UserRegistrationDaoImp getInstance() {
        if (obj == null) {
            obj = new UserRegistrationDaoImp();
        }
        return obj;
    }

    @Override
    public String createUser(User user) {
        return "INSERT INTO "+ USER_TABLE +
                " (" +USER_ID_COLUMN+ "," + USER_FIRST_NAME_COLUMN + "," +
                 USER_LAST_NAME_COLUMN +"," + USER_PHONE_NUMBER_COLUMN + "," +
                USER_EMAIL_ID_COLUMN + "," + USER_PASSWORD_COLUMN + "," + USER_CITY_COLUMN + "," +
                USER_PROVINCE_COLUMN + "," + USER_ADDRESS_COLUMN + "," + USER_POSTAL_CODE_COLUMN + "," +
                USER_ROLE_ID_COLUMN + "," + USER_IS_EMPLOYEE_COLUMN + "," + USER_IS_APPROVED_COLUMN + ")" +
                " VALUES (" +
                "\"" + user.getUserId() + "\"," +
                "\"" + user.getFirstName() + "\"," +
                "\"" + user.getLastName() + "\"," +
                "\"" + user.getPhoneNumber() + "\"," +
                "\"" + user.getEmailId() + "\"," +
                "\"" + user.getPassword() + "\"," +
                "\"" + user.getCity() + "\"," +
                "\"" + user.getProvince() + "\"," +
                "\"" + user.getAddress() + "\"," +
                "\"" + user.getPostalCode() + "\"," +
                "\"" + user.getRoleId() + "\"," +
                 user.isEmployee() + "," +
                 user.isApproved()  +
                ");";
    }

    @Override
    public String getUserById(int id) {
        return "SELECT * FROM "+USER_TABLE+" WHERE "+USER_ID_COLUMN+" = " + id + ";";
    }

    @Override
    public String getUsers() {
        return "SELECT * FROM " + USER_TABLE +";";
    }

    @Override
    public String deleteUserById(int id) {
        return "DELETE FROM "+USER_TABLE+" WHERE "+USER_ID_COLUMN+" = " + id + ";";
    }
}
