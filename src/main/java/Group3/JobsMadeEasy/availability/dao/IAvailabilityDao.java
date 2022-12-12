package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;

public interface IAvailabilityDao {
    String createAvailability (Availability availability);
    String viewAllAvailability();
    String viewAvailabilityById(int id);
    String deleteAvailabilityById(int id);

}
