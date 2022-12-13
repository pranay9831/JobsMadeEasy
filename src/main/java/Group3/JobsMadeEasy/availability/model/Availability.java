package Group3.JobsMadeEasy.availability.model;
import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import java.sql.SQLException;
import java.util.List;

public class Availability {
    private int availabilityId;
    private int availableDays;
    private String availableHours;
    private int userId;

    private IAvailabilityDao availabilityDao;


    public Availability(int availabilityId, int availableDays, String availableHours, int userId)
    {
        this.availabilityId = availabilityId;
        this.availableDays = availableDays;
        this.availableHours = availableHours;
        this.userId = userId;

    }

    public Availability(IAvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
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

    public String createAvailability(Availability availability, int id) throws JobsMadeEasyException {

        if (availability == null)
        {
            throw new JobsMadeEasyException("no such availability found");
        }

        return this.availabilityDao.createAvailability(availability, id);



    }

    public List<AvailabilityUserName> getAllAvailability() throws JobsMadeEasyException, SQLException {
        return this.availabilityDao.viewAllAvailability();


    }
    public List<Availability> getMyAvailability( int id) throws JobsMadeEasyException, SQLException {

        if (id == 0) {
            throw new JobsMadeEasyException("No Availability found in DB");
        }
        return availabilityDao.viewAvailabilityById(id);

    }

    public boolean deleteAvailabilityById(int id) throws JobsMadeEasyException, SQLException {

    return availabilityDao.deleteAvailabilityById(id);
    }


}
