package Group3.JobsMadeEasy.availability.model;

public class AvailabilityUserName {

    private String  firstName;
    private String lastName;
    private int availableDays;
    private String availableHours;

    public AvailabilityUserName(String firstName, String lastName, int availableDays, String availableHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.availableDays = availableDays;
        this.availableHours = availableHours;
    }
}
