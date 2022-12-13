package Group3.JobsMadeEasy.feedback.dao;

import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.feedback.model.Feedback;
import Group3.JobsMadeEasy.feedback.querygenerator.IFeedbackQueryGenerator;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


@Component
public class FeedbackDaoImp implements IFeedbackDao{

    private final IFeedbackQueryGenerator feedbackQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;

    private final HttpSession session;

    public FeedbackDaoImp(IFeedbackQueryGenerator feedbackDao, DatabaseSetup databaseSetup, IFeedbackQueryGenerator feedbackQueryGenerator, HttpSession session ) throws
            SQLException, IOException, ClassNotFoundException {
        this.feedbackQueryGenerator = feedbackQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.session = session;
        this.statement = connection.createStatement();
    }

    @Override
    public String createFeedback(@ModelAttribute Feedback feedback) throws JobsMadeEasyException {

        feedback.setFeedbackId(GenerateIdUtil.Object().generateRandomId());
        feedback.setExperience(feedback.getExperience());
        feedback.setComments(feedback.getComments());
        feedback.setOverAllRating(feedback.getOverAllRating());
        try {
            String createFeedbackQuery = feedbackQueryGenerator.createFeedback(feedback);
            System.out.println("------------------- " + createFeedbackQuery);
            int updatedRows = statement.executeUpdate(createFeedbackQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRows > 0) {
                return "index";
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return "myApplication";

    }

    @Override
    public List<Feedback> getFeedback() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        List<Feedback> feedbacks = new LinkedList<>();
        try {
            String getFeedbackQuery = feedbackQueryGenerator.getFeedback();
            rs = statement.executeQuery(getFeedbackQuery);

            while (rs.next()) {
                int i = rs.getInt("feedbackId");
                String experience = rs.getString("experience");
                String comments = rs.getString("comments");
                String overAllRating = rs.getString("overAllRatings");
                Feedback feedback = new Feedback(i, experience, comments, overAllRating);
                feedbacks.add(feedback);
            }
            System.out.println(feedbacks);
            return feedbacks;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }

    }



    }

