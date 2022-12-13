package Group3.JobsMadeEasy.availability.model;

import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;

public class AvailabilityUserName {

    private IAvailabilityDao availabilityDao;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String firstName;
    private String lastName;
    private int availableDays;
    private String availableHours;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public AvailabilityUserName(IAvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    public AvailabilityUserName() {
    }

    public AvailabilityUserName(int userId, String firstName, String lastName, int availableDays, String availableHours) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.availableDays = availableDays;
        this.availableHours = availableHours;
    }

    @Override
    public String toString() {
        return "AvailabilityUserName{" +
                "availabilityDao=" + availabilityDao +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", availableDays=" + availableDays +
                ", availableHours='" + availableHours + '\'' +
                '}';
    }


}
