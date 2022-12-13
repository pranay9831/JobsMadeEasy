package Group3.JobsMadeEasy.jobpost.controller;

import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.model.JobPostMapper;
import Group3.JobsMadeEasy.jobpost.repository.IJobPostDao;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobPostController {
    private final IJobPostDao jobPostDao;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;

    public JobPostController(IJobPostDao jobPostDao, DatabaseSetup databaseSetup) throws SQLException, IOException,
            ClassNotFoundException {
        this.jobPostDao = jobPostDao;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
    }

    @PostMapping("/add_job_post")
    public String createJobPost(@ModelAttribute JobPost jobPost) throws JobsMadeEasyException, SQLException {
        if (jobPost == null) {
            throw new JobsMadeEasyException("Failed to add job post!!");
        } else {
            jobPost.setJobPostId(GenerateIdUtil.Object().generateRandomId());
            jobPost.setJobTitle(jobPost.getJobTitle());
            jobPost.setSalary(jobPost.getSalary());
            jobPost.setJobType(jobPost.getJobType());
            jobPost.setJobDescription(jobPost.getJobDescription());
            jobPost.setJobLocation(jobPost.getJobLocation());
            jobPost.setLanguageRequirements(jobPost.getLanguageRequirements());
        }
        try {
            String createJobPostQuery = jobPostDao.createJobPost(jobPost);
            int updatedRowsInTable = statement.executeUpdate(createJobPostQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRowsInTable > 0) {
                return "hrHomePage";
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }

        return "createJobPost";
    }

    @GetMapping("/get_all_job_post")
    public List<JobPost> getAllJobPost() throws JobsMadeEasyException, SQLException {
        ResultSet resultSet = null;
        try {
            String getAllJobPostQuery = jobPostDao.viewAllJobs();
            resultSet = statement.executeQuery(getAllJobPostQuery);
            List<JobPost> jobPosts = new LinkedList<>();
            while (resultSet.next()) {
                int jobPostId = resultSet.getInt("jobPostId");
                String jobTitle = resultSet.getString("jobTitle");
                String salary = resultSet.getString("salary");
                String jobType = resultSet.getString("jobType");
                String jobDescription = resultSet.getString("jobDescription");
                String jobLocation = resultSet.getString("jobLocation");
                String languageRequired = resultSet.getString("languageRequired");
                JobPost jobPost = new JobPost(jobPostId, jobTitle, salary, jobType, jobDescription, jobLocation, languageRequired);
                jobPosts.add(jobPost);
            }
            return jobPosts;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
    }

    @GetMapping("/get_job_post_by_id/{id}")
    public Optional<JobPost> getJobPostById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        ResultSet resultSet = null;
        if (id == 0) {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        } else {
            try {
                String getJobPostByIdQuery = jobPostDao.viewJobById(id);
                resultSet = statement.executeQuery(getJobPostByIdQuery);
                if (resultSet.next()) {
                    return Optional.ofNullable((JobPost) new JobPostMapper().mapRow(resultSet, resultSet.getRow()));
                }
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            } finally {
                databaseSetup.closeDatabaseConnection();
                resultSet.close();
            }
        }
        return null;
    }

    @DeleteMapping("/delete_job_post_by_id/{id}")
    public boolean deleteJobPostById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        if (this.getJobPostById(id).isPresent()) {
            try {
                String deleteJobPostByIdQuery = jobPostDao.deleteJobById(id);
                statement.executeUpdate(deleteJobPostByIdQuery);
                return true;
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            } finally {
                databaseSetup.closeDatabaseConnection();
            }
        } else {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }

    @GetMapping("/hr_home_page")
    public String showHrHomePage(Model model) {
        return "hrHomePage";
    }

    @GetMapping("/create_job_post")
    public String viewCreateJobPost(Model model) {
        JobPost jobPost = new JobPost();
        model.addAttribute("jobPost", jobPost);
        return "createJobPost";
    }

    @GetMapping("/view_all_jobs")
    public String viewAllJobs(Model model) throws SQLException, JobsMadeEasyException {
        List<JobPost> jobPosts = getAllJobPost();
        model.addAttribute("jobPosts", jobPosts);
        return "viewAllJobs";
    }

    @GetMapping("/delete_job_by_id")
    public String deleteJobsById(Model model) throws SQLException, JobsMadeEasyException {
        List<JobPost> jobPosts = getAllJobPost();
        model.addAttribute("jobPosts", jobPosts);
        return "deleteJobById";
    }

}
