package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.querygenerator.IAvailabilityQueryGenerator;
import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Component
public class AvailabilityDaoImp implements IAvailabilityDao{

    private final IAvailabilityQueryGenerator availabilityQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;

    public AvailabilityDaoImp(IAvailabilityQueryGenerator availabilityQueryGenerator, DatabaseSetup databaseSetup) throws SQLException, IOException, ClassNotFoundException {
        this.availabilityQueryGenerator = availabilityQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
    }

    @Override
    public String createAvailability(Availability availability, int id) throws JobsMadeEasyException {


        availability.setAvailabilityId(GenerateIdUtil.Object().generateRandomId());
        availability.setAvailableDays(availability.getAvailableDays());
        availability.setAvailableHours(availability.getAvailableHours());
        availability.setUserId(id);

        try{
            String createUserQuery = availabilityQueryGenerator.createAvailability(availability);
            int updatedRows = statement.executeUpdate(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            if(updatedRows > 0){
                return "availabilityHomePage";
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
        }
        return "availability";

    }

    @Override
    public List<Availability> viewAllAvailability() throws JobsMadeEasyException, SQLException {

        ResultSet rs = null;
        try {
            String viewAllAvailabilityQuery = availabilityQueryGenerator.viewAllAvailability();
            rs = statement.executeQuery(viewAllAvailabilityQuery);
            List<Availability> availabilities = new LinkedList<>();
            return getAvailabilities(availabilities, rs);
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
    }

    @Override
    public List<Availability> viewAvailabilityById(int id) throws JobsMadeEasyException, SQLException {
        List<Availability> availabilities = null;
        ResultSet rs = null;
            try {
                String viewAvailabilityByIdQuery = availabilityQueryGenerator.viewAvailabilityById(id);
                rs = statement.executeQuery(viewAvailabilityByIdQuery);
                availabilities = new LinkedList<>();
                return getAvailabilities(availabilities, rs);

            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                rs.close();
            }
        }


    @Override
    public boolean deleteAvailabilityById(int id) throws JobsMadeEasyException {
        try {
            String deleteAvailabilityByIdQuery = availabilityQueryGenerator.deleteAvailabilityById(id);
            statement.executeUpdate(deleteAvailabilityByIdQuery);
            return true;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();

        }
    }

    public List<Availability> getAvailabilities(List<Availability> availabilities, ResultSet rs) throws SQLException {
        while(rs.next()){
            int availabilityId = rs.getInt("availabilityId");
            int availableDays = rs.getInt("availableDays");
            String availableHours = rs.getString("availableHours");
            int userId = rs.getInt("userId");

            Availability availability = new Availability(availabilityId,availableDays,availableHours,userId);
            availabilities.add(availability);
        }
        return availabilities;
    }



}
