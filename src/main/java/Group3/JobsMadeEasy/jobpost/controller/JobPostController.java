package Group3.JobsMadeEasy.jobpost.controller;
import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.repository.IJobPostRepository;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobPostController {

    private final IJobPostRepository jobPostRepository;
    public JobPostController(IJobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }


    @GetMapping("/hr_home_page")
    public String showHrHomePage(Model model)
    {
        return "hrHomePage";
    }

    @PostMapping("/add_job_post")
    public boolean createJobPost(@ModelAttribute JobPost jobPost) throws JobsMadeEasyException {
        if (jobPost == null)
        {
             throw new JobsMadeEasyException("Failed to add job post.");
        }
        else
        {
            jobPost.setJobPostId(GenerateIdUtil.Object().generateRandomId());
            jobPost.setJobTitle(jobPost.getJobTitle());
            jobPost.setSalary(jobPost.getSalary());
            jobPost.setJobType(jobPost.getJobType());
            jobPost.setJobDescription(jobPost.getJobDescription());
            jobPost.setJobLocation(jobPost.getJobLocation());
            jobPost.setLanguageRequirements(jobPost.getLanguageRequirements());
            System.out.println(jobPost);
        }
        this.jobPostRepository.createJobPost(jobPost);
        return true;
    }

    @GetMapping("/get_all_job_post")
    public List<JobPost> getAllJobPost()
    {
        List<JobPost> jobPost= new ArrayList<>();
        List<JobPost> newList = this.jobPostRepository.viewAllJobs();
        newList.forEach(x -> {jobPost.add(x);});
        return jobPost;
    }
    
    @GetMapping("/get_job_post_by_id/{id}")
    public Optional<JobPost> getJobPostById(@PathVariable int id) throws JobsMadeEasyException {
        Optional jobPost = null;
        if(id == 0)
        {
            throw new JobsMadeEasyException("No job post with given id.!!");
        }
        else 
        {
            jobPost = this.jobPostRepository.viewJobById(id);
            
        }
        return jobPost;
    }

    @DeleteMapping("/delete_job_post_by_id/{id}")
    public boolean deleteJobPostById(@PathVariable int id) throws JobsMadeEasyException
    {
        if (this.getJobPostById(id).isPresent())
        {
            return this.jobPostRepository.deleteJobById(id);
        }
        else
        {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }


    @GetMapping("/view_all_jobs")
    public String viewAllJobs(Model model)
    {
        List<JobPost> jobPosts = getAllJobPost();
        model.addAttribute("jobPosts", jobPosts);
        return "viewAllJobs";
    }

    @GetMapping("/create_job_post")
    public String viewCreateJobPost(Model model)
    {
        JobPost jobPost = new JobPost();
        model.addAttribute("jobPost", jobPost);
        return "createJobPost";
    }

    @GetMapping("/view_job_by_id")
    public String viewJobsById(Model model)
    {
        return "viewJobById";
    }

    @GetMapping("/delete_job_by_id")
    public String deleteJobsById(Model model)
    {
        List<JobPost> jobPosts = getAllJobPost();
        model.addAttribute("jobPosts", jobPosts);
        return "deleteJobById";
    }

//    @GetMapping("/create_job_post")
//    public String showJobPostCreationForm(Model model)
//    {
//        JobPost jobPost = new JobPost();
//        model.addAttribute("jobPost", jobPost);
//        return "createJobPost";
//    }

}
