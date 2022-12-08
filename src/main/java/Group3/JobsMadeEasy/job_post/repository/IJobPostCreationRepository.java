package Group3.JobsMadeEasy.job_post.repository;


import Group3.JobsMadeEasy.job_post.model.JobPost;

import java.util.List;
import java.util.Optional;

public interface IJobPostCreationRepository
{
    boolean createJobPost (JobPost jobPost);
    List<JobPost> viewAllJobs();
    Optional viewAllJobsById(int id);
    int deleteJobById(int id);
    List getById(int id);


}
