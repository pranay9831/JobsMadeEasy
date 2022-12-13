package Group3.JobsMadeEasy.feedback.querygenerator;

import Group3.JobsMadeEasy.feedback.model.Feedback;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.feedback.querygenerator.FeedbackConstant.*;
import static Group3.JobsMadeEasy.jobapplication.querygenerator.JobApplicationConstant.JOB_APPLICATION_TABLE;

@Component
public class FeedbackQueryGenerator implements IFeedbackQueryGenerator {
        private static FeedbackQueryGenerator obj;

        public static FeedbackQueryGenerator getInstance() {
            if (obj == null) {
                obj = new FeedbackQueryGenerator();
            }
            return obj;
        }

        public String createFeedback(Feedback feedback) {
            return "INSERT INTO "+ FEEDBACK_TABLE +
                    " (" +FEEDBACK_ID_COLUMN+ "," + EXPERIENCE_COLUMN + "," +
                    COMMENTS_COLUMN +"," + OVER_ALL_RATING_COLUMN +")"+
                    " VALUES (" +
                    "\"" + feedback.getFeedbackId() + "\"," +
                    "\"" + feedback.getExperience() + "\"," +
                    "\"" + feedback.getComments() + "\"," +
                    "\"" + feedback.getOverAllRating() + "\"" +

                    ");";
        }

        @Override
        public String getFeedback() {

            return "SELECT * FROM " + FEEDBACK_TABLE +";";
        }


}



