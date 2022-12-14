package Group3.JobsMadeEasy.feedback.model;


import Group3.JobsMadeEasy.feedback.dao.IFeedbackDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class Feedback {


    private int feedbackId;
    private String experience;
    private String comments;
    private String overAllRating;
    private IFeedbackDao feedbackDao;
    private IFeedbackDao getFeedbackDao;


    public Feedback(int feedbackId, String experience, String comments, String overAllRating) {

        this.feedbackId=feedbackId;
        this.experience=experience;
        this.comments=comments;
        this.overAllRating=overAllRating;
    }

    public Feedback (IFeedbackDao feedbackDao){

        this.getFeedbackDao= feedbackDao;
    }

    public Feedback() {

    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOverAllRating() {
        return overAllRating;
    }

    public void setOverAllRating(String overAllRating) {
        this.overAllRating = overAllRating;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", experience='" + experience + '\'' +
                ", comments='" + comments + '\'' +
                ", overAllRating='" + overAllRating + '\'' +
                '}';
    }

    public String createFeedback(Feedback feedback) throws JobsMadeEasyException, SQLException {

        if (feedback == null) {
            throw new JobsMadeEasyException("NO FEEDBACK PROVIDED");
        }
        return this.feedbackDao.createFeedback(feedback);
    }

    public List<Feedback> getFeedback() throws SQLException, JobsMadeEasyException {

        return this.getFeedbackDao.getFeedback();
    }

}
