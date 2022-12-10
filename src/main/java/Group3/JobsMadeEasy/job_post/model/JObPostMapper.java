package Group3.JobsMadeEasy.job_post.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JObPostMapper implements RowMapper
{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new JobPost(
                rs.getInt("job_post_id"),
                rs.getString("job_title"),
                rs.getString("salary"),
                rs.getString("job_type"),
                rs.getString("job_description"),
                rs.getString("job_location"),
                rs.getString("language_required")
                );
    }


}
