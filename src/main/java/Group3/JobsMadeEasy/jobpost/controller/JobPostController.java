package Group3.JobsMadeEasy.jobpost.controller;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;
import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class JobPostController {
    private final JobPost jobPost;

    public JobPostController(IJobPostDao jobPostDao)
    {
        this.jobPost = new JobPost(jobPostDao);
    }


    @GetMapping("/hr_home_page")
    public String showHrHomePage() {
        return "hrHomePage";
    }

    @GetMapping("/create_job_post")
    public String viewCreateJobPost(Model model) {
        JobPost jobPost = new JobPost();
        model.addAttribute("jobPost", jobPost);
        return "createJobPost";
    }

    @GetMapping("/view_all_jobs")
    public String viewAllJobs(Model model) {
        List<JobPost> jobPosts;
        try
        {
            jobPosts= getAllJobPost();
        }
        catch (SQLException | JobsMadeEasyException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("jobPosts", jobPosts);
        return "viewAllJobs";
    }

    @GetMapping("/delete_job_by_id")
    public String deleteJobsById(Model model) throws SQLException, JobsMadeEasyException {
        List<JobPost> jobPosts = getAllJobPost();
        model.addAttribute("jobPosts", jobPosts);
        return "deleteJobById";
    }


    @PostMapping("/add_job_post")
    public String createJobPost(@ModelAttribute JobPost jobPost) throws JobsMadeEasyException, SQLException {

        return this.jobPost.createJobPost(jobPost);
    }

    @GetMapping("/get_all_job_post")
    public List<JobPost> getAllJobPost() throws JobsMadeEasyException, SQLException {

        return jobPost.getAllJobPost();
    }


    @GetMapping("/get_job_post_by_id/{id}")
    public Optional<JobPost> getJobPostById(@PathVariable int id) throws JobsMadeEasyException, SQLException {

        return jobPost.getJobPostById(id);
    }

    @DeleteMapping("/delete_job_post_by_id/{id}")
    public boolean deleteJobPostById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return jobPost.deleteJobPostById(id);
    }

}
