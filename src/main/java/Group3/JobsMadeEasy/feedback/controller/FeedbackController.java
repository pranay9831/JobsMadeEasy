package Group3.JobsMadeEasy.feedback.controller;


import Group3.JobsMadeEasy.feedback.dao.IFeedbackDao;
import Group3.JobsMadeEasy.feedback.model.Feedback;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class FeedbackController {

    private final Feedback feedback;

    public FeedbackController(IFeedbackDao feedbackDao) {

        this.feedback = new Feedback(feedbackDao);
    }

    @PostMapping("/save/feedback")
    public String createFeedback(@ModelAttribute Feedback feedback) throws JobsMadeEasyException, SQLException {
            return this.feedback.createFeedback(feedback);
        }

    @GetMapping("/view_feedback")
    public List<Feedback> getFeedback() throws JobsMadeEasyException, SQLException {
        return feedback.getFeedback();
    }

    @GetMapping("/view_all_feedback")
    public String viewFeedback(Model model)
    {
        List<Feedback> feedbacks;
        try
        {
            feedbacks = getFeedback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("feedbacks", feedbacks);
        return "viewFeedback";
    }

    @GetMapping("/create_feedback_application")
    public String viewCreateFeedbackApplication (Model model)
    {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "myApplication";
    }


}



