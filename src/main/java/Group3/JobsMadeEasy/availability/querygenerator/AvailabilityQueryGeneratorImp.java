package Group3.JobsMadeEasy.availability.querygenerator;

import Group3.JobsMadeEasy.availability.model.Availability;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.authentication.user.querygenerator.registration.UserRegistrationConstant.USER_ID_COLUMN;
import static Group3.JobsMadeEasy.availability.querygenerator.AvailabilityConstant.*;

@Component
public class AvailabilityQueryGeneratorImp implements IAvailabilityQueryGenerator {

    private static AvailabilityQueryGeneratorImp obj;

    private AvailabilityQueryGeneratorImp() {
    }

    public static AvailabilityQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new AvailabilityQueryGeneratorImp();
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
        return "DELETE FROM " + AVAILABILITY_TABLE+ "WHERE " + AVAILABILITY_ID_COLUMN+" = " + id + ";";

    }




}
