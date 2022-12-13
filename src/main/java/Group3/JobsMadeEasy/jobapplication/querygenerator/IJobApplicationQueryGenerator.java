package Group3.JobsMadeEasy.jobapplication.querygenerator;

import Group3.JobsMadeEasy.jobapplication.model.JobApplication;

public interface IJobApplicationQueryGenerator {

    String createJobApplication(JobApplication jobApplication);
    String getJobApplicationById(int id);
    String getJobApplications();
    String  deleteJobApplicationById(int id);
}
