package Group3.JobsMadeEasy.jobpost.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobPostMapper implements RowMapper
{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new JobPost(
                rs.getInt("jobPostId"),
                rs.getString("jobTitle"),
                rs.getString("salary"),
                rs.getString("jobType"),
                rs.getString("jobDescription"),
                rs.getString("jobLocation"),
                rs.getString("languageRequired")
                );
    }


}
