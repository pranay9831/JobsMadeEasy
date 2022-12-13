package Group3.JobsMadeEasy.feedback.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Feedback(
                rs.getInt("feedbackId"),
                rs.getString("experience"),
                rs.getString("comments"),
                rs.getString("overAllRating")

        );
    }


}
