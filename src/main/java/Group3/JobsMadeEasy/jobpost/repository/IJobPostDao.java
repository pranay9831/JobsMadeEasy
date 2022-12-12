package Group3.JobsMadeEasy.jobpost.repository;

import Group3.JobsMadeEasy.jobpost.model.JobPost;


public interface IJobPostDao
{
    String createJobPost(JobPost jobPost);
    String viewAllJobs();
    String viewJobById(int id);
    String deleteJobById(int id);
//    String updateJobPost (JobPost jobPost, int id);
}
