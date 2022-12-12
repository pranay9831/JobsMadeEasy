package Group3.JobsMadeEasy.availability.model;

public class Availability {
    private int availabilityId;
    private int availableDays;
    private String availableHours;
    private int userId;


    public Availability(int availabilityId, int availableDays, String availableHours, int userId)
    {
        this.availabilityId = availabilityId;
        this.availableDays = availableDays;
        this.availableHours = availableHours;
        this.userId = userId;

    }
    public Availability(){

    }
    public int getAvailabilityId()
    {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getAvailableDays()
    {
        return availableDays;
    }

    public void setAvailableDays(Integer availableDays) {
        this.availableDays = availableDays;
    }

    public String getAvailableHours(){ return availableHours; }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public int getUserId(){return userId; }

    public void setUserId(Integer userId){this.userId=userId; }

    @Override
    public String toString() {
        return "Availability{" +
                "availabilityId=" + availabilityId +
                ", availableDays='" + availableDays + '\'' +
                ", availableHours='" + availableHours + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
