package Group3.JobsMadeEasy.jobapplication.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobApplicationMapper implements RowMapper<JobApplication> {

@Override
    public JobApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new JobApplication(
                rs.getInt("applicationId"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getInt("expectedSalary"),
                rs.getString("currentEmployeeStatus"),
                rs.getInt("yearPassOut"),
                rs.getString("applicationType"),
                rs.getString("studyField"),
                rs.getString("degreeType"),
                rs.getString("university"),
                rs.getString("expertOne"),
                rs.getString("expertTwo"),
                rs.getString("expertThree"),
                rs.getInt("jobPostId")
        );
    }


}
