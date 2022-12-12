package Group3.JobsMadeEasy.availability.model;

import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailabilityMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Availability(
                rs.getInt("availabilityId"),
                rs.getInt("availableDays"),
                rs.getString("availableHours"),
                rs.getInt("userId")

        );
    }

}
