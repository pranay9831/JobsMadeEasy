package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AvailabilityDaoImpTest {
    @Test
    public void createAvailabilityTest() {
        Availability availability = new Availability(1,5, "monday 4", 4);
        AvailabilityDaoImp availabilityDaoImp = AvailabilityDaoImp.getInstance();
        String queryGenerated = availabilityDaoImp.createAvailability(availability);
        String queryExpected = "INSERT INTO availability (availabilityId,availableDays,availableHours,userId)"+
                "VALUES (\"1\",\"5\",\"monday 4\",\"4\");";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void viewAvailabilityByIdTest() {
        int userId = 1;
        AvailabilityDaoImp availabilityDaoImp = AvailabilityDaoImp.getInstance();
        String queryGenerated = availabilityDaoImp.viewAvailabilityById(userId);
        String queryExpected = "SELECT * FROM availability WHERE userId = " + userId + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void viewAllAvailabilityTest() {
        AvailabilityDaoImp availabilityDaoImp = AvailabilityDaoImp.getInstance();
        String queryGenerated = availabilityDaoImp.viewAllAvailability();
        String queryExpected = "SELECT * FROM availability;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void deleteAvailabilityByIdTest(){
        int availabilityId = 1;
        AvailabilityDaoImp availabilityDaoImp = AvailabilityDaoImp.getInstance();
        String queryGenerated = availabilityDaoImp.deleteAvailabilityById(availabilityId);
        String queryExpected = "DELETE FROM availability WHERE availabilityId = " + availabilityId + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query match");
    }
}


