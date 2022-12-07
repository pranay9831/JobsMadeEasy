package Group3.JobsMadeEasy.authentication.user.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testUserClass() {
        User user = Mockito.spy(new User());
        user.getUserId();
        user.getFirstName();
        user.getLastName();
        user.getPhoneNumber();
        user.getEmailId();
        user.getPassword();
        user.getCity();
        user.getProvince();
        user.getAddress();
        user.getPostalCode();
        user.getRoleId();
        user.isEmployee();
        user.isApproved();
        user.setUserId(1);
        user.setFirstName("Deep");
        user.setLastName("Dave");
        user.setPhoneNumber("12345678");
        user.setEmailId("test@gmail.com");
        user.setPassword("test@123");
        user.setCity("Halifax");
        user.setProvince("NS");
        user.setAddress("Dalhousie");
        user.setPostalCode("B2k 2Z2");
        user.setRoleId(1);
        user.setEmployee(false);
        user.setApproved(false);
        assertEquals(user.getUserId(), 1);
        assertEquals(user.getFirstName(), "Deep");
        assertEquals(user.getLastName(), "Dave");
        assertEquals(user.getPhoneNumber(), "12345678");
        assertEquals(user.getEmailId(), "test@gmail.com");
        assertEquals(user.getPassword(), "test@123");
        assertEquals(user.getCity(), "Halifax");
        assertEquals(user.getProvince(), "NS");
        assertEquals(user.getAddress(), "Dalhousie");
        assertEquals(user.getPostalCode(), "B2k 2Z2");
        assertEquals(user.getRoleId(), 1);
        assertEquals(user.isEmployee(), false);
        assertEquals(user.isApproved(),false);
    }
}
