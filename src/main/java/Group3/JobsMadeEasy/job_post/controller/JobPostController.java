package Group3.JobsMadeEasy.job_post.controller;
import Group3.JobsMadeEasy.job_post.model.JobPost;
import Group3.JobsMadeEasy.job_post.repository.IJobPostCreationRepository;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class JobPostController {

    private final IJobPostCreationRepository jobPostCreationRepository;

    public JobPostController(IJobPostCreationRepository jobPostCreationRepository) {
        this.jobPostCreationRepository = jobPostCreationRepository;
    }

    @GetMapping("/createJobPostForm")
    public String showJobPostCreationForm(Model model)
    {
        JobPost jobPost = new JobPost();
        model.addAttribute("jobPost", jobPost);
        return "jobPost";
    }

    @PostMapping("/jobPost")
    public String createJobPost(@ModelAttribute JobPost jobPost) throws JobsMadeEasyException {
        System.out.println("index");
        if (jobPost == null)
        {
             throw new JobsMadeEasyException("nu such job post found");
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
        }
        this.jobPostCreationRepository.createJobPost(jobPost);
        return "index";
    }

    @GetMapping("/job-post")
    public List<JobPost> getAllJobPost()
    {

        List<JobPost> jobPost= new ArrayList<>();
        List<JobPost> newList = this.jobPostCreationRepository.viewAllJobs();
        newList.forEach(x -> {jobPost.add(x);});
        return jobPost;
    }
    
    @GetMapping("/job-post/{id}")
    public Optional<JobPost> getJobById(@PathVariable int id) throws JobsMadeEasyException {
        Optional jobPost = null;
        if(id == 0)
        {
            throw new JobsMadeEasyException("No job post with given id.!!");
        }
        else 
        {
            jobPost = this.jobPostCreationRepository.viewAllJobsById(id);
            
        }
        return jobPost;
    }

    @DeleteMapping("/job-post/{id}")
    public boolean deleteJobPostById(@PathVariable int id) throws JobsMadeEasyException
    {
        if (this.getJobById(id).isPresent())
        {
            this.jobPostCreationRepository.deleteJobById(id);
            return true;
        }
        else
        {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }

}
