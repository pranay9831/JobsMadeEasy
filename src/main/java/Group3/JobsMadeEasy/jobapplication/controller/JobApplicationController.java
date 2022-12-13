package Group3.JobsMadeEasy.jobapplication.controller;
import Group3.JobsMadeEasy.jobapplication.dao.IJobApplicationDao;
import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class JobApplicationController {

    private final JobApplication jobApplication;

    public JobApplicationController(IJobApplicationDao jobApplicationDao) {
        this.jobApplication = new JobApplication(jobApplicationDao);
    }

    @GetMapping("/createApplicationForm")
    public String showJobApplicationCreationForm(Model model) {
        JobApplication jobApplication = new JobApplication();
        model.addAttribute("jobApplication", jobApplication);
        return "jobApplication";
    }

    @PostMapping("/save/jobApplication")
    public String createJobApplication(@ModelAttribute JobApplication jobApplication) throws JobsMadeEasyException, SQLException {
        return this.jobApplication.createJobApplication(jobApplication);
    }


        @GetMapping("/view_all_job_application")
        public List<JobApplication> getJobApplication() throws JobsMadeEasyException, SQLException {
            return jobApplication.getAllJobApplication();
        }

        @GetMapping("/view_all_application")
        public String viewAllJobApplication(Model model)
        {
            List<JobApplication> jobApplications;
            try
            {
                jobApplications = getJobApplication();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (JobsMadeEasyException e) {
                throw new RuntimeException(e);
            }
            model.addAttribute("jobApplications", jobApplications);
            return "viewAllApplication";


        }

        @GetMapping("/get_job_application_by_id")

        public Optional<JobApplication> getJobApplicationById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
            return jobApplication.getJobApplicationById(id);
        }


        @DeleteMapping("/delete_job_application_by_id/{id}")
        public boolean deleteJobApplicationById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
            return jobApplication.deleteJobApplicationById(id);
        }

        @GetMapping("/applicant_home_page")
        public String jobApplication (Model model)
        {

            return "jobApplication";
        }


        @GetMapping("/create_job_application")
        public String viewCreateJobApplication (Model model)
        {
            JobApplication jobApplication = new JobApplication();
            model.addAttribute("jobApplication", jobApplication);
            return "myApplication";
        }


        @GetMapping("/delete_application")
        public String deleteApplication (Model model) throws JobsMadeEasyException, SQLException {
        List<JobApplication> jobApplication = getJobApplication();
        model.addAttribute("jobApplication", jobApplication);
        return "deleteApplication";
    }
    }


