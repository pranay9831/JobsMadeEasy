package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import static Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationConstant.USER_ID_COLUMN;
import static Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationConstant.USER_TABLE;
import static Group3.JobsMadeEasy.availability.dao.AvailabilityConstant.*;

@Component
public class AvailabilityDaoImp implements IAvailabilityDao {

    private static AvailabilityDaoImp obj;

    private AvailabilityDaoImp() {
    }

    public static AvailabilityDaoImp getInstance() {
        if (obj == null) {
            obj = new AvailabilityDaoImp();
        }
        return obj;
    }

    @Override
    public String createAvailability(Availability availability) {
        return "INSERT INTO "+ AVAILABILITY_TABLE+
                " (" + AVAILABILITY_ID_COLUMN + "," +
                AVAILABLE_DAYS_COLUMN + "," +
                AVAILABLE_HOURS_COLUMN + "," +
                USER_ID_COLUMN+  ")" +
                "VALUES (" +
                "\"" + availability.getAvailabilityId() + "\"," +
                "\"" + availability.getAvailableDays() + "\"," +
                "\"" + availability.getAvailableHours() + "\"," +
                "\"" + availability.getUserId() + "\"" +
                ");";


    }

    @Override
    public String viewAllAvailability() {
        return  "SELECT * FROM " + AVAILABILITY_TABLE +";";



    }

    @Override
    public String viewAvailabilityById(int id) {
        return  "SELECT * FROM "+AVAILABILITY_TABLE+" WHERE "+USER_ID_COLUMN+" = " + id + ";";


    }

    @Override
    public String deleteAvailabilityById(int id) {
        return "DELETE FROM availability WHERE availabilityId = " + id + ";";

    }




}
