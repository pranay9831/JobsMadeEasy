package Group3.JobsMadeEasy.job_post.hr.repository;


import Group3.JobsMadeEasy.job_post.hr.model.JobPost;

public interface IJobPostCreationRepository
{
    boolean createJobPost (JobPost jobPost);
}
