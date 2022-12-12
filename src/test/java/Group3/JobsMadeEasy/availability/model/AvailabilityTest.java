package Group3.JobsMadeEasy.availability.model;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailabilityTest {
    @Test
    public void tesAvailabilityClass() {
        Availability availability = Mockito.spy(new Availability());
        availability.getAvailabilityId();
        availability.getAvailableDays();
        availability.getAvailableHours();
        availability.getUserId();

        availability.setAvailabilityId(1);
        availability.setAvailableDays(5);
        availability.setAvailableHours("monday 4");
        availability.setUserId(5);
        assertEquals(availability.getAvailabilityId(), 1);
        assertEquals(availability.getAvailableDays(), 5);
        assertEquals(availability.getAvailableHours(), "monday 4");
        assertEquals(availability.getUserId(), 5);

    }
}
