package Group3.JobsMadeEasy.jobpost.model;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;

public class SimpleJobPostFactory implements IJobPostFactory {

    @Override
    public JobPost createJobPost() {
        return new SimpleJobPost();
    }

    @Override
    public JobPost createJobPost(IJobPostDao jobPostDao) {
        return new SimpleJobPost(jobPostDao);
    }
}
