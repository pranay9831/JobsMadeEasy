package Group3.JobsMadeEasy.job_post.hr.controller;

import Group3.JobsMadeEasy.job_post.hr.exception.JobPostException;
import Group3.JobsMadeEasy.job_post.hr.model.JobPost;
import Group3.JobsMadeEasy.job_post.hr.repository.IJobPostCreationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobPostController {

    private final IJobPostCreationRepository jobPostCreationRepository;

    @Autowired
    public JobPostController(IJobPostCreationRepository jobPost, IJobPostCreationRepository jobPostCreationRepository) {
        this.jobPostCreationRepository = jobPostCreationRepository;
    }

    @PostMapping("/createJobPost")
    public boolean createJobPost(@RequestBody JobPost jobPost) throws JobPostException, JsonProcessingException {
        if (jobPost == null) {
            throw new JobPostException("No such job post found!!");} else {
            jobPost.setJobPostId(jobPost.getJobPostId());
            jobPost.setJobTitle(jobPost.getJobTitle());
            jobPost.setSalary(jobPost.getSalary());
            jobPost.setJobType(jobPost.getJobType());
            jobPost.setJobDescription(jobPost.getJobDescription());
            jobPost.setJobLocation(jobPost.getJobLocation());
            jobPost.setLanguageRequirements(jobPost.getLanguageRequirements());
            jobPost.setApproved(true);
        }
        this.jobPostCreationRepository.createJobPost(jobPost);
        return false;
    }
}
