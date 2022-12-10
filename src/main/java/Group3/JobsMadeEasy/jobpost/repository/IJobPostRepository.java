package Group3.JobsMadeEasy.jobpost.repository;


import Group3.JobsMadeEasy.jobpost.model.JobPost;

import java.util.List;
import java.util.Optional;

public interface IJobPostRepository
{
    JobPost createJobPost (JobPost jobPost);
    List<JobPost> viewAllJobs();
    Optional viewJobById(int id);
    boolean deleteJobById(int id);

}
